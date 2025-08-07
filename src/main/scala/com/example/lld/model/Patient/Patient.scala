package com.example.lld.model.Patient


case class Patient(id: PatientId, name: String, age:Int, gender:Gender)

case object Patient {
  def apply(name: String, age:Int, gender:Gender): Patient =
    Patient(PatientId(), name, age,gender)
}

