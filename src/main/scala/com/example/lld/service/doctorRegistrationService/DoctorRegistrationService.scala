package com.example.lld.service.doctorRegistrationService

import com.example.lld.model.Doctor.{Doctor, DoctorId, DoctorSpeciality}

/**
 * A new doctor should be able to register, and mention his/her speciality among
 * (Cardiologist, Dermatologist, Orthopedic, General Physician)
 * A doctor should be able to declare his/her availability in each slot for the day.
 * For example, the slots will be of 30 mins like 9am-9.30am, 9.30am-10am
 */
trait DoctorRegistrationService {
  // Define service methods here
  def registerDoctor( name:String, speciality: DoctorSpeciality): Doctor
  def getDoctorById(id:DoctorId): Doctor
  def validateDoctorId(id:DoctorId): Boolean
}
