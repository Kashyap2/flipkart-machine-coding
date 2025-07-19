package com.example.lld.model

/** Base trait for all strongly‑typed identifiers.
  * The concrete type supplies a prefix; an 8‑digit numeric suffix is auto‑generated.
  */

trait StringId {
  def value: String
  override def toString: String = value
}

object StringId {
  def generate(prefix: String, length: Int = 8): String =
    s"${prefix}_${scala.util.Random.alphanumeric.filter(_.isDigit).take(length).mkString}"
}

// Generic ID companion factory
trait StringIdCompanion[T <: StringId] {
  def prefix: String
  def apply(): T
}