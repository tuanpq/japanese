package io.github.tuanpq.data.remote.model.mapper

import io.github.tuanpq.data.remote.model.Grammar
import io.github.tuanpq.domain.model.ExampleEntity
import io.github.tuanpq.domain.model.GrammarEntity
import io.github.tuanpq.utility.CustomMapper
import javax.inject.Inject

class GrammarMapper @Inject constructor() : CustomMapper<Grammar, GrammarEntity> {

    override fun mapList(from: List<Grammar>): List<GrammarEntity> {
        val result = mutableListOf<GrammarEntity>()
        for (grammar in from) {
            val examples = mutableListOf<ExampleEntity>()
            if (grammar.examples.isNotEmpty()) {
                grammar.examples.forEach { example ->
                    val exampleEntity = ExampleEntity(
                        id = example.id,
                        sentence = example.sentence,
                        meaning = example.meaning
                    )
                    examples.add(exampleEntity)
                }
            }
            val grammarEntity = GrammarEntity(
                id = grammar.id,
                jlptLevel = grammar.jlptLevel,
                expression = grammar.expression,
                explanation = grammar.explanation,
                examples = examples
            )
            result.add(grammarEntity)
        }
        return result
    }

    override fun mapObject(from: Grammar): GrammarEntity {
        val examples = mutableListOf<ExampleEntity>()
        if (from.examples.isNotEmpty()) {
            from.examples.forEach { example ->
                val exampleEntity = ExampleEntity(
                    id = example.id,
                    sentence = example.sentence,
                    meaning = example.meaning
                )
                examples.add(exampleEntity)
            }
        }
        val grammarEntity = GrammarEntity(
            id = from.id,
            jlptLevel = from.jlptLevel,
            expression = from.expression,
            explanation = from.explanation,
            examples = examples
        )
        return grammarEntity
    }

}