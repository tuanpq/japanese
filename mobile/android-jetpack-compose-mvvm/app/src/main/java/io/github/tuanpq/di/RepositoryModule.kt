package io.github.tuanpq.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.tuanpq.domain.repository.GrammarRepository
import io.github.tuanpq.domain.repository.GrammarRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindGrammarRepository(grammarRepositoryImpl: GrammarRepositoryImpl): GrammarRepository

}