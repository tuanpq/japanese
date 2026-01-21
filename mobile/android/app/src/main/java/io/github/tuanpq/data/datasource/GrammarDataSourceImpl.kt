package io.github.tuanpq.data.datasource

import io.github.tuanpq.data.remote.GrammarService
import io.github.tuanpq.data.remote.model.Grammar
import retrofit2.Response
import javax.inject.Inject

class GrammarDataSourceImpl @Inject constructor(private var apiService: GrammarService) : GrammarDataSource {

    override suspend fun findAllGrammars(): Response<List<Grammar>> {
        return apiService.findAllGrammars()
    }

}