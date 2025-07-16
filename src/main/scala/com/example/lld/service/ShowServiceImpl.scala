package com.example.lld.service

import com.google.inject.{Inject, Singleton}
import com.example.lld.model.{Movie, MovieId}
import com.example.lld.repository.BaseRepository

@Singleton
class ShowServiceImpl @Inject() (repo: BaseRepository[MovieId, Movie]) extends ShowService {
  override def addMovie(movie: Movie): Unit           = repo.save(movie)
  override def getMovie(id: MovieId): Option[Movie]   = repo.findById(id)
  override def listMovies(): List[Movie]              = repo.all()
}
