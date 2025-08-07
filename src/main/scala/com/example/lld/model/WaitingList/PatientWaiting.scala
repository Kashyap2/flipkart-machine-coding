package com.example.lld.model.WaitingList

import com.example.lld.model.Patient.PatientId
import com.example.lld.model.Slot.SlotId
import com.example.lld.model.WaitingList.PatientWaitingStatus.Waiting

case class PatientWaiting(id:WaitingId, patientId: PatientId, slotId: SlotId, status: PatientWaitingStatus)

case object PatientWaiting {

  def apply( patientId: PatientId, slotId: SlotId): PatientWaiting = PatientWaiting(WaitingId(), patientId, slotId,Waiting)
}
