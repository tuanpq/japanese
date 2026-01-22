package io.github.tuanpq.domain.repository

import io.github.tuanpq.data.remote.model.Grammar
import io.github.tuanpq.utility.APIState
import kotlinx.coroutines.flow.Flow

interface GrammarRepository {
    suspend fun findAllGrammars() : Flow<APIState<List<Grammar>>>
    suspend fun findGrammarById(grammarId: Int) : Flow<APIState<Grammar>>
}