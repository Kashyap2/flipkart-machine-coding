package com.example.lld.model

case class MovieId() extends Id{

  override def prefix: String = "MOV"
}
case class Movie(id: MovieId = MovieId(), title: String, durationMin: Int)
