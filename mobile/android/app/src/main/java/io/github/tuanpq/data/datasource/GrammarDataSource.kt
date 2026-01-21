package io.github.tuanpq.data.datasource

import io.github.tuanpq.data.remote.model.Grammar
import retrofit2.Response

interface GrammarDataSource {
    suspend fun findAllGrammars() : Response<List<Grammar>>
}