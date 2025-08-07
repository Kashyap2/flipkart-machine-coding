package com.example.lld.SlotRankingStrategy

import com.example.lld.model.Slot.Slot

sealed trait SlotRankingStrategy {
  def sort(list:List[Slot]): List[Slot]
}

case object RankingByStartTime extends SlotRankingStrategy {
  override def sort(list: List[Slot]): List[Slot] = list.sortWith((s1,s2) => s1.startTime.isAfter(s2.startTime))
}





