package com.example.lld.service.patientRegistrationService

import com.example.lld.model.Patient.{Gender, Patient, PatientId}

trait PatientRegistrationService {
  // Define service methods here
  def registerPatient( name:String, age:Int, gender: Gender): Patient
  def getPatientById(id:PatientId): Patient
  def validatePatientId(id:PatientId): Boolean
}
