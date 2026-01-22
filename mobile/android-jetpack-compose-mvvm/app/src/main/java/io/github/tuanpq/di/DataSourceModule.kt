package io.github.tuanpq.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.tuanpq.data.datasource.GrammarDataSource
import io.github.tuanpq.data.datasource.GrammarDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindGrammarDataSource(grammarDataSourceImpl: GrammarDataSourceImpl): GrammarDataSource

}