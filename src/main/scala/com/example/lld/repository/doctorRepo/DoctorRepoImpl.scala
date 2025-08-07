package com.example.lld.repository.doctorRepo

import com.example.lld.model.Doctor.{Doctor, DoctorId}
import com.example.lld.service.logger.LoggerService
import com.example.lld.util.IdNotFoundException
import com.google.inject.{Inject, Singleton}

import scala.collection.concurrent.TrieMap

@Singleton
class DoctorRepoImpl @Inject()(loggerService: LoggerService) extends DoctorRepo {
  // implementation
  private val REGISTRY = TrieMap.empty[DoctorId, Doctor]

  override def registerDoctor(doctor: Doctor): Doctor = REGISTRY.putIfAbsent(doctor.id, doctor) match {
    case Some(_) => registerDoctor(doctor.copy(id = DoctorId()))
    case None => loggerService.info("Doctor added" + doctor)
      doctor
  }

  override def getDoctorById(id: DoctorId): Doctor = {
    REGISTRY.get(id) match {
      case Some(entity) => entity
      case None => throw  IdNotFoundException(id)
    }
  }

  override def validateDoctorId(id: DoctorId): Boolean = REGISTRY.exists(d => d._1==id)
}