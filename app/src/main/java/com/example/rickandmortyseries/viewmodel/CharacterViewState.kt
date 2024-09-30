package com.example.rickandmortyseries.viewmodel

import com.example.rickandmortyseries.data.Character
import com.example.rickandmortyseries.data.ErrorType

/**
 * Sealed class to represent the state of the character list.
 */
sealed class CharacterViewState() {

    /**
     * Result loaded successfully.
     */
    data class Success(val characterList: List<Character>) : CharacterViewState()

    /**
     * Result loaded failed.
     */
    data class Error(val errorType: ErrorType) : CharacterViewState()

}
