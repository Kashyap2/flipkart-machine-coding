package com.example.lld.model.Slot

sealed trait SlotStatus

object SlotStatus {
  case object Available extends SlotStatus
  case object Booked extends SlotStatus
  case object Completed extends SlotStatus
  case object Cancelled extends SlotStatus
}
