package com.example.lld.model.Doctor

sealed trait DoctorSpeciality

object DoctorSpeciality {
  case object Cardiologist extends DoctorSpeciality
  case object Dermatologist extends DoctorSpeciality
  case object Orthopedic extends DoctorSpeciality
  case object GeneralPhysician extends DoctorSpeciality
}
