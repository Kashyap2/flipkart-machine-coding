package com.example.lld.service.slotValidationService

import com.example.lld.model.Slot.Slot
import com.example.lld.repository.SlotRepo.SlotRepo
import com.example.lld.service.logger.LoggerService
import com.example.lld.service.slotValidationService.SlotValidationService.{END_TIME, START_TIME}
import com.google.inject.{Inject, Singleton}

import java.time.{Duration, LocalDateTime, LocalTime}

@Singleton
class SlotValidationServiceImpl @Inject()(loggerService: LoggerService, slotRepo: SlotRepo) extends SlotValidationService {
  // Implement service methods here

  override def validateSlot(slot: Slot): Boolean = {
    loggerService.info((slot,checkIntervalIsNotInPast(slot.startTime), slotRepo.hasOverlappingSlot(slot),validateSlotInterval(slot.startTime, slot.endTime)).toString())
    checkIntervalIsNotInPast(slot.startTime) && !slotRepo.hasOverlappingSlot(slot) && validateSlotInterval(slot.startTime, slot.endTime)
  }

  private def validateSlotInterval(startTime: LocalDateTime, endTime: LocalDateTime): Boolean = {
    val duration = Duration.between(startTime, endTime)
    duration.toMinutes == 30 && isWithinDailyTimeWindow(startTime, endTime, START_TIME, END_TIME)
  }


  private def isWithinDailyTimeWindow(startDateTime: LocalDateTime, endDateTime: LocalDateTime,
                                      allowedStart: LocalTime, allowedEnd: LocalTime): Boolean = {
    val startTime = startDateTime.toLocalTime
    val endTime = endDateTime.toLocalTime
    !startTime.isBefore(allowedStart) && !endTime.isAfter(allowedEnd)
  }

  override def checkIntervalIsNotInPast(startTime: LocalDateTime): Boolean = {
    !startTime.isBefore(LocalDateTime.now().withSecond(0).withNano(0))
  }
}


