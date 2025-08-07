package com.example.lld

import com.example.lld.di.Module
import com.example.lld.model.Doctor.DoctorSpeciality.{Cardiologist, Dermatologist, GeneralPhysician, Orthopedic}
import com.example.lld.model.Patient.Gender.{Female, Male}
import com.example.lld.service.doctorRegistrationService.DoctorRegistrationService
import com.example.lld.service.patientRegistrationService.PatientRegistrationService
import com.example.lld.service.slotManager.SlotManager
import com.google.inject.Guice

import java.time.{LocalDate, LocalDateTime, LocalTime}

object Main extends App {
  private val injector = Guice.createInjector(new Module)

  private def test(): Unit = {
    val patientService = injector.getInstance(classOf[PatientRegistrationService])
    val doctorService = injector.getInstance(classOf[DoctorRegistrationService])
    val slotService = injector.getInstance(classOf[SlotManager])

    val adarsh = patientService.registerPatient("Adarsh", 28, Male)
    val alice = patientService.registerPatient("Alice", 28, Female)
    val adarsh1 = patientService.registerPatient("Adarsh1", 28, Male)
    val alice1 = patientService.registerPatient("Alice1", 28, Female)

    val DOC1 = doctorService.registerDoctor("Adarsh DOC", Cardiologist)
    val DOC2 = doctorService.registerDoctor("Alice DOC", GeneralPhysician)
    val DOC3 = doctorService.registerDoctor("Adarsh1 DOC", Orthopedic)
    val DOC4 = doctorService.registerDoctor("Alice1 DOC", Dermatologist)

    val startTime1  =  LocalTime.of(9, 0)
    val endTime1 = LocalTime.of(9,30)

    val today: LocalDate = LocalDate.now()
    val windowStart: LocalDateTime = LocalDateTime.of(today, startTime1)
    val windowEnd: LocalDateTime = LocalDateTime.of(today, endTime1)

    val windowStar1: LocalDateTime = LocalDateTime.of(today.plusDays(1), startTime1)
    val windowEnd1: LocalDateTime = LocalDateTime.of(today.plusDays(1), endTime1)

    val slot1 =slotService.markSlotAvailableForDoctor(doctorId = DOC1.id, windowStart, endTime = windowEnd)
    val slot2 =slotService.markSlotAvailableForDoctor(doctorId = DOC1.id, windowStart.plusMinutes(31), endTime = windowEnd.plusMinutes(31))
    val slot3 =slotService.markSlotAvailableForDoctor(doctorId = DOC3.id, windowStart.plusMinutes(61), endTime = windowEnd.plusMinutes(61))

    println(slot1)
    println(slot2)
    println(slot3)

    val slot11 =slotService.markSlotAvailableForDoctor(doctorId = DOC1.id, windowStar1, endTime = windowEnd1)
    val slot12 =slotService.markSlotAvailableForDoctor(doctorId = DOC1.id, windowStar1.plusMinutes(31), endTime = windowEnd1.plusMinutes(31))
    val slot13 =slotService.markSlotAvailableForDoctor(doctorId = DOC3.id, windowStar1.plusMinutes(61), endTime = windowEnd1.plusMinutes(61))


    val bookedSlot1= slotService.bookSlotByPatientId(adarsh.id, slot1.id)
   val bookedSlot2= slotService.bookSlotByPatientId(alice1.id, slot2.id)
   val bookedSlot3= slotService.bookSlotByPatientId(adarsh1.id, slot3.id)

    val cancelSlot4= slotService.cancelSlot(slot3.id,adarsh1.id)


    println(slotService.getAppointmentForDayByDoctorId(DOC1.id))
    println(slotService.getAppointmentForDayByDoctorId(DOC3.id))


  }

  test()
}
