package com.example.lld.di

import com.example.lld.service.logger.{LoggerService, LoggerServiceImpl}
import com.google.inject.AbstractModule

class Module extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[LoggerService]).to(classOf[LoggerServiceImpl]).asEagerSingleton()
  }
}



