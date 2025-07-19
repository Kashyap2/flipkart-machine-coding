package com.example.lld

import com.google.inject.Guice
import com.example.lld.di.Module

object Main extends App {
  private val injector    = Guice.createInjector(new Module)

  private def test(): Unit = {
    //val showService = injector.getInstance(classOf[ShowService])

  }

  test()
}
