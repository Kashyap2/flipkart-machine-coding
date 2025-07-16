package com.example.lld

import com.google.inject.Guice
import com.example.lld.di.AppModule
import com.example.lld.model.{Movie, MovieId}
import com.example.lld.service.ShowService

object Main extends App {
  val injector    = Guice.createInjector(new AppModule)
  val showService = injector.getInstance(classOf[ShowService])

  val movie = Movie(title = "Avatar", durationMin = 162) // id auto‑generated
  showService.addMovie(movie)

  println(showService.listMovies())
}
