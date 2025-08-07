package com.example.lld.service.slotManager

import com.example.lld.SlotRankingStrategy.SlotRankingStrategy
import com.example.lld.model.Doctor.DoctorId
import com.example.lld.model.Patient.PatientId
import com.example.lld.model.Slot.{Slot, SlotId}
import com.example.lld.model.WaitingList.PatientWaiting

import java.time.LocalDateTime

trait SlotManager {
  // Define service methods here
  def markSlotAvailableForDoctor(doctorId: DoctorId, startTime: LocalDateTime, endTime: LocalDateTime): Slot

  def bookSlotByPatientId(patientId: PatientId, slotId: SlotId): Either[PatientWaiting, Slot]

  def cancelSlot(slotId: SlotId, patientId: PatientId): Slot

  def getAppointmentForDayByPatientId(patientId: PatientId, rankingStrategy: SlotRankingStrategy): List[Slot]

  def getAppointmentForDayByDoctorId(doctorId: DoctorId): List[Slot]
}

