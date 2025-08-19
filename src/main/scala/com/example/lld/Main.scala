package com.example.lld

import com.example.lld.di.Module
import com.google.inject.Guice

object Main extends App {
  private val injector = Guice.createInjector(new Module)

  private def test(): Unit = {
    println("it is wordking")
  }

  test()
}
