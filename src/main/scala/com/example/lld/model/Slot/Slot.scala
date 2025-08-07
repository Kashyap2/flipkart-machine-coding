package com.example.lld.model.Slot

import com.example.lld.model.Doctor.DoctorId
import com.example.lld.model.Patient.PatientId
import com.example.lld.model.Slot.SlotStatus.Available

import java.time.{LocalDate, LocalDateTime}

case class Slot(id: SlotId,
                startTime: LocalDateTime,
                endTime: LocalDateTime,
                doctorId: DoctorId,
                patientIdOpt: Option[PatientId],
               status: SlotStatus) {

   def isOverLapping(starTime1:LocalDateTime, endTime1:LocalDateTime): Boolean = {
     val x = !(starTime1.isAfter(endTime) || endTime1.isBefore(startTime))
     if(x) println(s"$this has overlapped with interval $starTime1 to $endTime1")
     x
  }
}

case object Slot {

   def apply(startTime: LocalDateTime, endTime: LocalDateTime, doctorId: DoctorId): Slot =
     Slot(SlotId(),startTime, endTime, doctorId, None, Available)
}

