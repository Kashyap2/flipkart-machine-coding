package com.example.lld.service.patientRegistrationService

import com.example.lld.model.Patient.{Gender, Patient, PatientId}
import com.example.lld.repository.patientRepo.PatientRepo
import com.google.inject.{Inject, Singleton}
import com.example.lld.service.logger.LoggerService

@Singleton
class PatientRegistrationServiceImpl @Inject()(patientRepo: PatientRepo) extends PatientRegistrationService {
  // Implement service methods here

  override def registerPatient(name: String, age: Int, gender: Gender): Patient = {
    patientRepo.registerPatient(Patient(name,age, gender))
  }

  override def getPatientById(id: PatientId): Patient = {
    patientRepo.getPatientById(id)
  }

  override def validatePatientId(id: PatientId): Boolean = patientRepo.validatePatientId(id)
}
