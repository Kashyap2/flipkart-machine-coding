package com.example.lld.service.slotValidationService

import com.example.lld.model.Slot.Slot

import java.time.{LocalDateTime, LocalTime}

trait SlotValidationService {
  // Define service methods here
  def validateSlot(slot: Slot): Boolean

  def checkIntervalIsNotInPast(startTime: LocalDateTime): Boolean
}

object SlotValidationService {
  val START_TIME: LocalTime = LocalTime.of(8, 59)   // 9:00 AM
  val END_TIME: LocalTime = LocalTime.of(21, 0)    // 9:00 PM



}