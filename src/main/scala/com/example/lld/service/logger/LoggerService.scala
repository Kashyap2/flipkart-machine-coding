package com.example.lld.service.logger


trait LoggerService {
  def info(message: String): Unit
  def warn(message: String): Unit
  def error(message: String, throwable: Throwable = null): Unit
}