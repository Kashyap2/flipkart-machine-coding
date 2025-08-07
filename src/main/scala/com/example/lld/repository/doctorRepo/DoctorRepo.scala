package com.example.lld.repository.doctorRepo

import com.example.lld.model.Doctor.{Doctor, DoctorId}

trait DoctorRepo {
  // methods
  def registerDoctor(doctor: Doctor): Doctor

  def getDoctorById(id:DoctorId): Doctor

  def validateDoctorId(id: DoctorId): Boolean
}