package com.example.lld.model.Patient

sealed trait Gender

object Gender {
  case object Female extends Gender
  case object Male extends Gender
}