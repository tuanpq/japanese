package io.github.tuanpq.domain.usecase

import io.github.tuanpq.data.remote.model.mapper.GrammarMapper
import io.github.tuanpq.domain.model.GrammarEntity
import io.github.tuanpq.domain.repository.GrammarRepository
import io.github.tuanpq.utility.APIState
import io.github.tuanpq.utility.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GrammarUseCase @Inject constructor(
    private val grammarRepository: GrammarRepository,
    private val grammarMapper: GrammarMapper
) {
    suspend fun findAllGrammars() : Flow<UIState<List<GrammarEntity>>> {
        return grammarRepository.findAllGrammars().map { result ->
            when (result) {
                is APIState.Success -> {
                    UIState.Success(grammarMapper.map(result.data))
                }
                is APIState.Error -> UIState.Error(result.message)
            }
        }
    }

}