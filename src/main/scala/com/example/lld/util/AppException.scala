package com.example.lld.util

import com.example.lld.model.StringId

sealed abstract class AppException(msg: String)
  extends RuntimeException(msg)

final case class ValidationError(field: String, detail: String)
  extends AppException(s"$field: $detail")

case class IdNotFoundException(id:StringId) extends Exception(s"Invalid $id")