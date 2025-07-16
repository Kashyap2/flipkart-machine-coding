package com.example.lld.repository

import com.example.lld.model.{Movie, MovieId}
import com.google.inject.{Inject, Singleton}

import scala.collection.concurrent.TrieMap

@Singleton
class InMemoryShowRepository @Inject() extends BaseRepository[MovieId, Movie] {
  private val store = TrieMap.empty[MovieId, Movie]

  override def save(entity: Movie): Unit = store.putIfAbsent(entity.id, entity) match {
    case Some(_) => throw new Exception("Duplicate ID")
    case None    => ()
  }

  override def findById(id: MovieId): Option[Movie] = store.get(id)
  override def all(): List[Movie]                   = store.values.toList
}
