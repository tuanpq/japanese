package io.github.tuanpq.data.remote

object API {
    const val BASE_URL = "http://10.0.2.2:8080/api/"
    const val FIND_ALL_GRAMMARS = "grammar/all"
    const val FIND_GRAMMAR_BY_ID = "grammar/{id}"
    const val ADD_GRAMMAR = "grammar/add"
    const val UPDATE_GRAMMAR = "grammar/update/{id}"
    const val DELETE_GRAMMAR = "grammar/delete/{id}"
    const val DELETE_GRAMMAR_BY_IDS = "grammar/deleteByIds"
}