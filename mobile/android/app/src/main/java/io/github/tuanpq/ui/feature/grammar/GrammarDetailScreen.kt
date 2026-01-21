package io.github.tuanpq.ui.feature.grammar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.tuanpq.ui.viewmodel.GrammarDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrammarDetailScreen(
    onNavigateBack: () -> Unit,
    viewModel: GrammarDetailViewModel = hiltViewModel()
) {
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
            Text("Back to Grammar")
        }
    }
}