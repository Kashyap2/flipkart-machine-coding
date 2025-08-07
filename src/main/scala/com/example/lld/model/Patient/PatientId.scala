package com.example.lld.model.Patient

import com.example.lld.model.{StringId, StringIdCompanion}

case class PatientId(override val value: String) extends StringId

object PatientId extends StringIdCompanion[PatientId] {
  override val prefix: String = "PAT" // Assumes name like "RideId", "UserId"

  override def apply(): PatientId = PatientId(StringId.generate(prefix))
}