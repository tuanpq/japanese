package io.github.tuanpq.data.remote

import io.github.tuanpq.data.remote.model.Grammar
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GrammarService {

    @GET(API.FIND_ALL_GRAMMARS)
    suspend fun findAllGrammars(): Response<List<Grammar>>

    @GET(API.FIND_GRAMMAR_BY_ID)
    suspend fun findGrammarById(@Path("id") id: Int): Response<Grammar>

    @POST(API.ADD_GRAMMAR)
    suspend fun addGrammar(@Body grammar: Grammar): Response<Grammar>

    @PUT(API.UPDATE_GRAMMAR)
    suspend fun updateGrammar(@Path("id") id: Int, @Body grammar: Grammar): Response<Grammar>

    @DELETE(API.DELETE_GRAMMAR)
    suspend fun deleteGrammar(@Path("id") id: Int): Response<Void>

    @DELETE(API.DELETE_GRAMMAR_BY_IDS)
    suspend fun deleteAllGrammarsByIds(@Body ids: List<Int>): Response<Void>

}

