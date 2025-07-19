package com.example.lld.service.logger

import com.google.inject.{Inject, Singleton}

@Singleton
class LoggerServiceImpl @Inject()() extends LoggerService {

  override def info(message: String): Unit =
    println(s"[INFO] ${timestamp()} $message")

  override def warn(message: String): Unit =
    println(s"[WARN] ${timestamp()} $message")

  override def error(message: String, throwable: Throwable): Unit = {
    println(s"[ERROR] ${timestamp()} $message")
    if (throwable != null) {
      throwable.printStackTrace()
    }
  }

  private def timestamp(): String = java.time.Instant.now.toString
}
