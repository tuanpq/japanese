package io.github.tuanpq.ui.navigation

sealed class ApplicationScreen(val route: String) {

    object Home: ApplicationScreen("home")

    object Grammar: ApplicationScreen("grammar")

    object GrammarDetail: ApplicationScreen("grammar_detail/{grammarId}") {
        const val ARG_GRAMMAR_ID = "grammarId"
        fun grammarDetailRoute(grammarId: Int): String = "grammar_detail/$grammarId"
    }

}