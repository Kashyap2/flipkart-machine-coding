package com.example.lld.model.WaitingList

import com.example.lld.model.{StringId, StringIdCompanion}

case class WaitingId(override val value: String) extends StringId

object WaitingId extends StringIdCompanion[WaitingId] {
  override val prefix: String = "${NAME.dropRight(2)}" // Assumes name like "RideId", "UserId"

  override def apply(): WaitingId = WaitingId(StringId.generate(prefix))
}