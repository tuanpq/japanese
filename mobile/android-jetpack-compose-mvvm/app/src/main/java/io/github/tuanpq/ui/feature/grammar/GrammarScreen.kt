package io.github.tuanpq.ui.feature.grammar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.tuanpq.domain.model.GrammarEntity
import io.github.tuanpq.ui.viewmodel.GrammarViewModel
import io.github.tuanpq.utility.UIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrammarScreen(
    onNavigateBack: () -> Unit,
    onNavigateToGrammarDetail: (Int) -> Unit,
    viewModel: GrammarViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.findAllGrammars()
    }

    val uiState by viewModel.grammarsState.collectAsStateWithLifecycle()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (uiState) {
            is UIState.Loading -> {

            }

            is UIState.Success -> {
                val grammars = (uiState as UIState.Success<List<GrammarEntity>>).data
                for (grammar in grammars) {
                    println("Expression: ${grammar.expression}")
                    println("Explanation: ${grammar.explanation}")
                    println("")
                }

                Column(Modifier.fillMaxSize()) {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(grammars.size) { index ->
                            val grammar = grammars[index]
                            Column(
                                modifier = Modifier
                                    .clickable(onClick = {
                                        onNavigateToGrammarDetail(grammar.id)
                                    })
                                    .padding(16.dp)
                            ) {
                                Text(text = grammar.expression)
                                Text(text = grammar.explanation)
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
                        Text("Back to Home")
                    }
                }
            }

            is UIState.Error -> {
                // val errorMessage = (uiState as UIState.Error).message
                Box(
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Button(
                        onClick = {
                            onNavigateBack()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(8.dp),
                    ) {
                        Text("Back to Home")
                    }
                }
            }

        }


    }

}