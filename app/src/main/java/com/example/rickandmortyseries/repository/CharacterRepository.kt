package com.example.rickandmortyseries.repository

import com.example.rickandmortyseries.data.CharacterApi
import com.example.rickandmortyseries.data.Characters

/**
 * Repository class for character data.
 */
internal class CharacterRepository {
    private val api : CharacterApi = CharacterApi.api

    /**
     * Get character data.
     */
    suspend fun getCharacters(page: Int): Characters =
        api.getAllCharacters(page)

    /**
     * Get character data by name.
     */
    suspend fun getCharactersByName(name: String): Characters =
        api.getCharactersByName(name)
}