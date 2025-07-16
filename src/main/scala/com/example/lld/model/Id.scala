package com.example.lld.model

import scala.util.Random

/** Base trait for all strongly‑typed identifiers.
  * The concrete type supplies a prefix; an 8‑digit numeric suffix is auto‑generated.
  */
trait Id {
  def prefix: String
  lazy val value: String = s"${prefix}_${Random.alphanumeric.filter(_.isDigit).take(8).mkString}"
  override def toString: String = value
}
