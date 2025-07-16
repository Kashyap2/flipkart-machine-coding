package com.example.lld.util

sealed abstract class AppException(msg: String)
  extends RuntimeException(msg)

final case class ValidationError(field: String, detail: String)
  extends AppException(s"$field: $detail")