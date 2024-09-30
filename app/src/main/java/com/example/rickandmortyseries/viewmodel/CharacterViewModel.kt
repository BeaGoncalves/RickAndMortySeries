package com.example.rickandmortyseries.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyseries.common.network.NetworkChecker
import com.example.rickandmortyseries.data.Characters
import com.example.rickandmortyseries.data.ErrorType
import com.example.rickandmortyseries.repository.CharacterRepository
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for the character list.
 * @param application Application context.
 * @param networkChecker Network checker.
 */
internal class CharacterViewModel @JvmOverloads constructor(
    application: Application,
    private val networkChecker: NetworkChecker = NetworkChecker(application)
) : AndroidViewModel(application) {

    private val useCase by lazy { CharacterRepository()}

    private val mutableState: MutableLiveData<CharacterViewState> = MutableLiveData()

    /**
     * State of the character list.
     */
    val state: LiveData<CharacterViewState> = mutableState

    init {
        verifyConnectivity()
    }

    private fun verifyConnectivity() {
        if (networkChecker.hasInternet()) {
            getAllCharacters()
        } else {
            mutableState.value = CharacterViewState.Error(ErrorType.NETWORK_ERROR)
        }
    }

    fun filterByName(name: String) {
        viewModelScope.launch {
            try {
                val list = useCase.getCharactersByName(name)
                mutableState.value = CharacterViewState.Success(list.results)
            } catch (e: Throwable) {
                mutableState.value = CharacterViewState.Error(ErrorType.GENERIC_ERROR)
            }
        }
    }

     private fun getAllCharacters() {
        viewModelScope.launch {
            try {
                val characterList = useCase.getCharacters(1)
                mutableState.value = CharacterViewState.Success(characterList.results)
            } catch (e: Throwable) {
                mutableState.value = CharacterViewState.Error(ErrorType.GENERIC_ERROR)
            }
        }
    }

}