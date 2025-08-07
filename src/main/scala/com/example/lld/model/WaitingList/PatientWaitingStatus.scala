package com.example.lld.model.WaitingList

sealed trait PatientWaitingStatus

object PatientWaitingStatus {
  case object  Waiting extends PatientWaitingStatus
  case object  SuccessfullyAssignedSlot extends PatientWaitingStatus
}