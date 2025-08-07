package com.example.lld.repository.patientRepo

import com.example.lld.model.Doctor.DoctorId
import com.example.lld.model.Patient.{Patient, PatientId}
import com.example.lld.service.logger.LoggerService
import com.example.lld.util.IdNotFoundException
import com.google.inject.{Inject, Singleton}

import scala.collection.concurrent.TrieMap

@Singleton
class PatientRepoImpl @Inject()(loggerService: LoggerService) extends PatientRepo {
  // implementation

  private val REGISTRY = TrieMap.empty[PatientId, Patient]

  override def registerPatient(Patient: Patient): Patient = REGISTRY.putIfAbsent(Patient.id, Patient) match {
    case Some(_) => registerPatient(Patient.copy(id = PatientId()))
    case None => loggerService.info("Patient added" + Patient)
      Patient
  }

  override def getPatientById(id: PatientId): Patient = {
    REGISTRY.get(id) match {
      case Some(entity) => entity
      case None => throw IdNotFoundException(id)
    }
  }
  override def validatePatientId(id: PatientId): Boolean = REGISTRY.exists(d => d._1==id)

}