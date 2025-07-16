package com.example.lld.di

import com.google.inject.{AbstractModule, TypeLiteral}
import com.example.lld.repository.{BaseRepository, InMemoryShowRepository}
import com.example.lld.model.{Movie, MovieId}
import com.example.lld.service.{ShowService, ShowServiceImpl}

class AppModule extends AbstractModule {
  override def configure(): Unit = {
    bind(new TypeLiteral[BaseRepository[MovieId, Movie]]() {})
      .to(classOf[InMemoryShowRepository])
      .asEagerSingleton()
    bind(classOf[ShowService]).to(classOf[ShowServiceImpl]).asEagerSingleton()
  }
}
