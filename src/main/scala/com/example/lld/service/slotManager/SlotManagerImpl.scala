package com.example.lld.service.slotManager

import com.example.lld.SlotRankingStrategy.SlotRankingStrategy
import com.example.lld.model.Doctor.DoctorId
import com.example.lld.model.Patient.PatientId
import com.example.lld.model.Slot.{Slot, SlotId}
import com.example.lld.model.WaitingList.PatientWaiting
import com.example.lld.repository.SlotRepo.SlotRepo
import com.example.lld.service.doctorRegistrationService.DoctorRegistrationService
import com.google.inject.{Inject, Singleton}
import com.example.lld.service.logger.LoggerService
import com.example.lld.service.patientRegistrationService.PatientRegistrationService
import com.example.lld.service.slotValidationService.SlotValidationService
import com.example.lld.util.ValidationError

import java.time.{LocalDate, LocalDateTime}

@Singleton
class SlotManagerImpl @Inject()(loggerService: LoggerService, slotRepo: SlotRepo,
                                slotValidationService: SlotValidationService,
                                doctorRegistrationService: DoctorRegistrationService,
                                patientRegistrationService: PatientRegistrationService) extends SlotManager {
  // Implement service methods here

  override def markSlotAvailableForDoctor(doctorId: DoctorId, startTime: LocalDateTime, endTime: LocalDateTime): Slot = {
    val slot = Slot(startTime,endTime, doctorId)
    if(!slotValidationService.validateSlot(slot)) throw ValidationError(s"$startTime & $endTime", "is not valid")
    if(!doctorRegistrationService.validateDoctorId(doctorId)) throw ValidationError(s"$doctorId", "is not valid")
    slotRepo.markSlotAvailableForDoctor(slot)
  }

  override def bookSlotByPatientId(patientId: PatientId, slotId: SlotId): Either[PatientWaiting, Slot] = {
    if(!patientRegistrationService.validatePatientId(patientId)) throw ValidationError(s"$patientId", "is not valid")
    val  slot = slotRepo.getSlotById(slotId)
    if(!slotValidationService.checkIntervalIsNotInPast(slot.startTime)) throw throw ValidationError(s"$slot", "is not valid")
    slotRepo.bookSlotByPatientId(patientId, slotId)
  }

  override def cancelSlot(slotId: SlotId, patientId: PatientId): Slot = slotRepo.cancelSlot(slotId, patientId)

  override def getAppointmentForDayByPatientId(patientId: PatientId, slotRankingStrategy: SlotRankingStrategy): List[Slot] =
    slotRankingStrategy.sort(slotRepo.getAppointmentForDayByPatientId(patientId, LocalDate.now()))

  override def getAppointmentForDayByDoctorId(doctorId: DoctorId): List[Slot] = slotRepo.getAppointmentForDayByDoctorId(doctorId,LocalDate.now())
}
