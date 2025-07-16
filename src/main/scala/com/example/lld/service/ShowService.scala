package com.example.lld.service

import com.example.lld.model.{Movie, MovieId}

trait ShowService {
  def addMovie(movie: Movie): Unit
  def getMovie(id: MovieId): Option[Movie]
  def listMovies(): List[Movie]
}
