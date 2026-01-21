package io.github.tuanpq.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.github.tuanpq.ui.feature.grammar.GrammarDetailScreen
import io.github.tuanpq.ui.feature.grammar.GrammarScreen
import io.github.tuanpq.ui.feature.home.HomeScreen

@Composable
fun ApplicationNavigationHost(navController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navController,
        startDestination = ApplicationScreen.Home.route) {

        composable(ApplicationScreen.Home.route) {
            HomeScreen(
                onNavigateToGrammar = {
                    navController.navigate(ApplicationScreen.Grammar.route)
                }
            )
        }

        composable(
            route = ApplicationScreen.Grammar.route,
            arguments = emptyList()
        ) {
            GrammarScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToGrammarDetail = {
                    navController.navigate(ApplicationScreen.GrammarDetail.route)
                }
            )
        }

        composable(ApplicationScreen.GrammarDetail.route) {
            GrammarDetailScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

    }

}