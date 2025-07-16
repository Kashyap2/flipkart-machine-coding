package com.example.lld.model

/** Example concrete ID type for sellers */
case class SellerId() extends Id{
  override def prefix: String = "SEL"
}
