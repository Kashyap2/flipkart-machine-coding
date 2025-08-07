package com.example.lld.repository.SlotRepo

import com.example.lld.model.Doctor.DoctorId
import com.example.lld.model.Patient.PatientId
import com.example.lld.model.Slot.{Slot, SlotId}
import com.example.lld.model.WaitingList.PatientWaiting

import java.time.{LocalDate, LocalDateTime}

trait SlotRepo {
  // methods

  def markSlotAvailableForDoctor(slot: Slot): Slot

  def bookSlotByPatientId(patientId: PatientId, slotId: SlotId): Either[PatientWaiting, Slot]

  def cancelSlot(slotId: SlotId, patientId: PatientId): Slot

  def getAppointmentForDayByPatientId(patientId: PatientId, date: LocalDate = LocalDate.now()): List[Slot]

  def getAppointmentForDayByDoctorId(doctorId: DoctorId, date: LocalDate = LocalDate.now()): List[Slot]

  def getSlotForTheDay(date: LocalDate): List[Slot]

  def hasOverlappingSlot(slot:Slot): Boolean

  def getSlotById(slotId: SlotId):Slot
}