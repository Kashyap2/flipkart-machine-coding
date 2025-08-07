package com.example.lld.model.Doctor

case class Doctor(id:DoctorId, name:String, speciality: DoctorSpeciality)

case object Doctor {
   def apply(name: String, speciality: DoctorSpeciality): Doctor =
    Doctor(DoctorId(), name, speciality)
}


