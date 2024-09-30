package com.example.rickandmortyseries.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API interface for character data.
 */
internal interface CharacterApi {

    @GET(OpKeys.CHARACTER_URL)
    suspend fun getAllCharacters(@Query("page") page: Int) : Characters

    @GET(OpKeys.CHARACTER_URL)
    suspend fun getCharactersByName(@Query("name") name: String) : Characters

    /**
     * Operation keys.
     */
    private object OpKeys {
        const val CHARACTER_URL = "api/character"
    }

    companion object {
        /**
         * Retrofit instance.
         */
        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        /**
         * API instance.
         */
        val api: CharacterApi by lazy {
            retrofit.create(CharacterApi::class.java)
        }
    }
}