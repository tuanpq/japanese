package io.github.tuanpq.ui.feature.grammar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

                Column(Modifier.fillMaxSize()) {
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(grammars.size) { index ->
                            val grammar = grammars[index]
                            ElevatedCard(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 8.dp
                                ),
                                modifier = Modifier
                                    // .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                                    .clickable(onClick = {
                                        onNavigateToGrammarDetail(grammar.id)
                                    })
                            ) {
                                Text(
                                    modifier = Modifier.padding(8.dp),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    text = grammar.expression
                                )
                                HorizontalDivider(
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                                Text(
                                    modifier = Modifier.padding(8.dp),
                                    fontSize = 14.sp,
                                    text = grammar.explanation
                                )
                            }
                        }
                    }

                    Button(
                        onClick = {
                            onNavigateBack()
                        },
                        modifier = Modifier
                            .height(80.dp)
                            .padding(16.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                    ) {
                        Text("Back to Home")
                    }
                }
            }

            is UIState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Button(
                        onClick = {
                            onNavigateBack()
                        },
                        modifier = Modifier
                            .height(80.dp)
                            .padding(16.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                    ) {
                        Text("Back to Home")
                    }
                }
            }
        }
    }

}