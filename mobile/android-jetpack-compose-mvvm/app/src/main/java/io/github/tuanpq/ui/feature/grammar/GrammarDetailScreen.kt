package io.github.tuanpq.ui.feature.grammar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.tuanpq.domain.model.GrammarEntity
import io.github.tuanpq.ui.viewmodel.GrammarDetailViewModel
import io.github.tuanpq.utility.UIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrammarDetailScreen(
    onNavigateBack: () -> Unit,
    grammarId: Int,
    viewModel: GrammarDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = grammarId) {
        if (grammarId > 0) {
            viewModel.findGrammarById(grammarId)
        }
    }

    val uiState by viewModel.grammarDetailState.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (uiState) {
            is UIState.Loading -> {

            }

            is UIState.Success -> {
                val grammarDetail = (uiState as UIState.Success<GrammarEntity>).data
                Column(Modifier.fillMaxSize()) {
                    Text(text = grammarDetail.expression)
                    Text(text = grammarDetail.explanation)
                    HorizontalDivider()

                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(grammarDetail.examples.size) { index ->
                            val example = grammarDetail.examples[index]
                            Column(
                            ) {
                                Text(text = example.sentence)
                                Text(text = example.meaning)
                                HorizontalDivider()
                            }
                        }
                    }

                    Button(
                        onClick = {
                            onNavigateBack()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text("Back to Grammar")
                    }
                }
            }

            is UIState.Error -> {
                val errorMessage = (uiState as UIState.Error).message
            }
        }
    }

}