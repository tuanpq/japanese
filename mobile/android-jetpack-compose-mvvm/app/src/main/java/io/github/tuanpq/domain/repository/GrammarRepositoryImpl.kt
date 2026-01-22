package io.github.tuanpq.domain.repository

import io.github.tuanpq.data.datasource.GrammarDataSource
import io.github.tuanpq.data.remote.model.Grammar
import io.github.tuanpq.utility.APIState
import io.github.tuanpq.utility.DispatchersProvider
import io.github.tuanpq.utility.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GrammarRepositoryImpl @Inject constructor(
    private val grammarDataSource: GrammarDataSource,
    private val dispatchersProvider: DispatchersProvider
) : GrammarRepository {

    override suspend fun findAllGrammars() : Flow<APIState<List<Grammar>>> {
        return flow {
            val result = safeApiCall { grammarDataSource.findAllGrammars() }
            emit(result)
        }.flowOn(dispatchersProvider.io())
    }

    override suspend fun findGrammarById(grammarId: Int) : Flow<APIState<Grammar>> {
        return flow {
            val result = safeApiCall { grammarDataSource.findGrammarById(grammarId) }
            emit(result)
        }.flowOn(dispatchersProvider.io())
    }

}