package com.example.lld.model.Doctor

import com.example.lld.model.{StringId, StringIdCompanion}

case class DoctorId(override val value: String) extends StringId

object DoctorId extends StringIdCompanion[DoctorId] {
  override val prefix: String = "DOC" // Assumes name like "RideId", "UserId"

  override def apply(): DoctorId = DoctorId(StringId.generate(prefix))
}