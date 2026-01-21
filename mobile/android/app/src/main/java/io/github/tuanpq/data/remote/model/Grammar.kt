package io.github.tuanpq.data.remote.model

data class Grammar(
    val id: Int,
    val jlptLevel: String,
    val expression: String,
    val explanation: String,
    val examples: List<Example>
)
