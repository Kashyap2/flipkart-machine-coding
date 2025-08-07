package com.example.lld.repository.patientRepo

import com.example.lld.model.Patient.{Patient, PatientId}

trait PatientRepo {
  // methods
  def registerPatient(Patient: Patient): Patient

  def getPatientById(id: PatientId): Patient

  def validatePatientId(id: PatientId): Boolean
}