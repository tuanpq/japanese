package io.github.tuanpq.domain.model

data class GrammarEntity(
    val id: Int,
    val jlptLevel: String,
    val expression: String,
    val explanation: String,
    val examples: List<ExampleEntity>
)
