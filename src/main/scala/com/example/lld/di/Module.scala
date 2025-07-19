package com.example.lld.di

import com.example.lld.service.logger.{LoggerService, LoggerServiceImpl}
import com.google.inject.AbstractModule

class Module extends AbstractModule {
  override def configure(): Unit = {
    //bind(classOf[ShowService]).to(classOf[ShowServiceImpl]).asEagerSingleton()
    bind(classOf[LoggerService]).to(classOf[LoggerServiceImpl]).asEagerSingleton()
    //bind(classOf[DoctorRegistrationService]).to(classOf[DoctorRegistrationServiceImpl]).asEagerSingleton()


  }
}


