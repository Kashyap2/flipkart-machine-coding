package com.example.lld.repository

trait BaseRepository[ID, E] {
  def save(entity: E): Unit
  def findById(id: ID): Option[E]
  def all(): List[E]
}
