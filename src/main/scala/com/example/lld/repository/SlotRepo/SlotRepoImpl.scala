package com.example.lld.repository.SlotRepo

import com.example.lld.model.Doctor.{DoctorId, DoctorSpeciality}
import com.example.lld.model.Patient.PatientId
import com.example.lld.model.Slot.SlotStatus.{Booked, Cancelled}
import com.example.lld.model.Slot.{Slot, SlotId, SlotStatus}
import com.example.lld.model.WaitingList.PatientWaitingStatus.{SuccessfullyAssignedSlot, Waiting}
import com.example.lld.model.WaitingList.{PatientWaiting, WaitingId}
import com.example.lld.repository.doctorRepo.DoctorRepo
import com.example.lld.service.logger.LoggerService
import com.example.lld.util.{IdNotFoundException, ValidationError}
import com.google.inject.{Inject, Singleton}

import java.time.{LocalDate, LocalDateTime}
import scala.annotation.tailrec
import scala.collection.concurrent.TrieMap

@Singleton
class SlotRepoImpl @Inject()(loggerService: LoggerService) extends SlotRepo {
  // implementation
  private  val SLOT_REPO = TrieMap.empty[SlotId, Slot]
  private val WAITING_LIST_REPO = TrieMap.empty[WaitingId, PatientWaiting]

  override def markSlotAvailableForDoctor(slot: Slot): Slot =
    SLOT_REPO.putIfAbsent(slot.id, slot) match {
      case Some(_) => markSlotAvailableForDoctor(slot.copy(id = SlotId()))
      case None => loggerService.info(s"Slot added $slot")
        slot
    }

  override def bookSlotByPatientId(patientId: PatientId, slotId: SlotId): Either[PatientWaiting, Slot] = {
    SLOT_REPO.get(slotId) match {
      case Some(slot) =>
        if(slot.patientIdOpt.isDefined && slot.patientIdOpt.get==patientId)
          throw new Error("same patient rebooking same slot")
        slot.status match {
          case SlotStatus.Cancelled |  SlotStatus.Available=>
            val newSlot = slot.copy(status = Booked, patientIdOpt=Some(patientId))
            SLOT_REPO.update(newSlot.id,newSlot)
            Right(newSlot)
          case _ => Left(addToWaitingList(PatientWaiting(patientId, slotId)))
        }
      case None => throw IdNotFoundException(slotId)
    }
  }

  override def cancelSlot(slotId: SlotId, patientId: PatientId): Slot = {
    SLOT_REPO.get(slotId) match {
      case Some(slot) =>
        val slotPatientId = slot.patientIdOpt.getOrElse(throw new Exception("Paitent has not booked yet" + patientId))
        if(slotPatientId !=patientId) throw new Exception("patient and slot mimatched")
        slot.status match {
          case SlotStatus.Booked =>
            val updatedSlot = slot.copy(status = Cancelled)
            val newSlot = assignSlotToWaitingList(updatedSlot)
            SLOT_REPO.update(newSlot.id, newSlot)
            newSlot
          case _ => throw ValidationError(slot.toString,"Invlid cacellation request")
        }
      case None => throw IdNotFoundException(slotId)

    }
  }

  override def getAppointmentForDayByPatientId(patientId: PatientId, date: LocalDate = LocalDate.now() ): List[Slot] = {
    SLOT_REPO.values.filter(slot =>
      slot.patientIdOpt match {
        case Some(p) => p == patientId && isSlotInInterval(slot, date)
        case None => false
      }).toList
  }

  override def getAppointmentForDayByDoctorId(doctorId: DoctorId, date: LocalDate = LocalDate.now()): List[Slot] = {
    SLOT_REPO.values.filter(slot => slot.doctorId==doctorId && isSlotInInterval(slot, date)).toList
  }

  override def getSlotForTheDay(date:LocalDate): List[Slot] = {
    SLOT_REPO.values.filter(slot => isSlotInInterval(slot,date)).toList
  }

  private def assignSlotToWaitingList(slot:Slot) = {
    val slotId = slot.id
    val waitingOpt = WAITING_LIST_REPO.values.find(w => w.slotId==slotId && w.status==Waiting)
    waitingOpt match {
      case Some(waiting) =>
        val assigned = waiting.copy(status = SuccessfullyAssignedSlot)
        WAITING_LIST_REPO.update(waiting.id, assigned)
        slot.copy(status = Booked, patientIdOpt = Some(assigned.patientId))
      case None => loggerService.info("no other patient to move from waiting list")
        slot
    }
  }

  @tailrec
  private def addToWaitingList(p:PatientWaiting): PatientWaiting = {
    WAITING_LIST_REPO.putIfAbsent(p.id, p) match {
      case Some(_) => addToWaitingList(p.copy(id = WaitingId()))
      case None => loggerService.info(s"Waiting list for $p added")
        p
    }
  }

  private def isSlotInInterval(slot: Slot,date: LocalDate) = {
    slot.startTime.isAfter(date.atStartOfDay()) && slot.endTime.isBefore(date.plusDays(1).atStartOfDay())
  }



  override def hasOverlappingSlot(slot: Slot): Boolean = {
   SLOT_REPO.exists {
      case (_, slotRepo:Slot) => slotRepo.doctorId==slot.doctorId && slotRepo.isOverLapping(slot.startTime, slot.endTime)
    }
  }

  override def getSlotById(slotId: SlotId): Slot = SLOT_REPO.getOrElse(slotId, throw IdNotFoundException(slotId))
}