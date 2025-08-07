package com.example.lld.model.Slot

import com.example.lld.model.{StringId, StringIdCompanion}

case class SlotId(override val value: String) extends StringId

object SlotId extends StringIdCompanion[SlotId] {
  override val prefix: String = "SLT" // Assumes name like "RideId", "UserId"

  override def apply(): SlotId = SlotId(StringId.generate(prefix))
}