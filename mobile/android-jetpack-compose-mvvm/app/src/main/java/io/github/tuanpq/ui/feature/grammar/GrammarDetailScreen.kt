package io.github.tuanpq.ui.feature.grammar

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    OutlinedCard(
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                        ),
                        border = BorderStroke(0.5.dp, Color.Black.copy(alpha = 0.2f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            modifier = Modifier.padding(8.dp),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            text = grammarDetail.expression
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            modifier = Modifier.padding(8.dp),
                            fontSize = 14.sp,
                            text = grammarDetail.explanation
                        )
                    }

                    LazyColumn(modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(grammarDetail.examples.size) { index ->
                            val example = grammarDetail.examples[index]
                            ElevatedCard(
                                elevation = CardDefaults.cardElevation(
                                    defaultElevation = 8.dp
                                ),
                                modifier = Modifier
                            ) {
                                Text(
                                    modifier = Modifier.padding(8.dp),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    text = example.sentence
                                )
                                HorizontalDivider(
                                    modifier = Modifier.padding(horizontal = 8.dp)
                                )
                                Text(
                                    modifier = Modifier.padding(8.dp),
                                    fontSize = 14.sp,
                                    text = example.meaning
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
                        Text("Back to Grammar")
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
                        Text("Back to Grammar")
                    }
                }
            }
        }
    }

}