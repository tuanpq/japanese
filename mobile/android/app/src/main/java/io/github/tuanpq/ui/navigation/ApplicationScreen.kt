package io.github.tuanpq.ui.navigation

sealed class ApplicationScreen(val route: String) {

    object Home: ApplicationScreen("home")

    object Grammar: ApplicationScreen("grammar")

    object GrammarDetail: ApplicationScreen("grammar_detail")

}