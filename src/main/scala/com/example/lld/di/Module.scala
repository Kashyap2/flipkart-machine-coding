package com.example.lld.di

import com.example.lld.repository.SlotRepo.{SlotRepo, SlotRepoImpl}
import com.example.lld.repository.doctorRepo.{DoctorRepo, DoctorRepoImpl}
import com.example.lld.repository.patientRepo.{PatientRepo, PatientRepoImpl}
import com.example.lld.service.doctorRegistrationService.{DoctorRegistrationService, DoctorRegistrationServiceImpl}
import com.example.lld.service.logger.{LoggerService, LoggerServiceImpl}
import com.example.lld.service.patientRegistrationService.{PatientRegistrationService, PatientRegistrationServiceImpl}
import com.example.lld.service.slotManager.{SlotManager, SlotManagerImpl}
import com.example.lld.service.slotValidationService.{SlotValidationService, SlotValidationServiceImpl}
import com.google.inject.AbstractModule

class Module extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[DoctorRepo]).to(classOf[DoctorRepoImpl]).asEagerSingleton()
    bind(classOf[PatientRepo]).to(classOf[PatientRepoImpl]).asEagerSingleton()
    bind(classOf[SlotRepo]).to(classOf[SlotRepoImpl]).asEagerSingleton()

    bind(classOf[LoggerService]).to(classOf[LoggerServiceImpl]).asEagerSingleton()
    bind(classOf[SlotManager]).to(classOf[SlotManagerImpl]).asEagerSingleton()
    bind(classOf[DoctorRegistrationService]).to(classOf[DoctorRegistrationServiceImpl]).asEagerSingleton()

    bind(classOf[PatientRegistrationService]).to(classOf[PatientRegistrationServiceImpl]).asEagerSingleton()
    bind(classOf[SlotValidationService]).to(classOf[SlotValidationServiceImpl]).asEagerSingleton()


  }
}



