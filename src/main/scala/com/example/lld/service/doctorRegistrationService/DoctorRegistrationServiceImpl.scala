package com.example.lld.service.doctorRegistrationService

import com.example.lld.model.Doctor.{Doctor, DoctorId, DoctorSpeciality}
import com.example.lld.repository.doctorRepo.DoctorRepo
import com.example.lld.service.logger.LoggerService
import com.google.inject.{Inject, Singleton}

@Singleton
class DoctorRegistrationServiceImpl @Inject()(doctorRepo: DoctorRepo) extends DoctorRegistrationService {
  // Implement service methods here

  override def registerDoctor(name: String, speciality: DoctorSpeciality): Doctor = {
    doctorRepo.registerDoctor(Doctor(name, speciality))
  }

  override def validateDoctorId(id: DoctorId): Boolean = doctorRepo.validateDoctorId(id)

  override def getDoctorById(id: DoctorId): Doctor = doctorRepo.getDoctorById(id)
}
