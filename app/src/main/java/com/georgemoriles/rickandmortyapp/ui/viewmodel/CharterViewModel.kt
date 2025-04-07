package com.georgemoriles.rickandmortyapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.georgemoriles.rickandmortyapp.data.CharacterRepository
import com.georgemoriles.rickandmortyapp.data.model.CharacterItem
import com.georgemoriles.rickandmortyapp.domain.GetCharacterUseCase
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel() {

    private var currentPage = 1

    private val repository: CharacterRepository = TODO()

    var characterItem = MutableLiveData<CharacterItem>()
    var characterList = MutableLiveData<List<CharacterItem>>(emptyList())
    var getCharacterUseCase = GetCharacterUseCase()
    var isLoading = MutableLiveData<Boolean>()


    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getCharacterUseCase(1)

            /*if(result?.isEmpty() == false){
                if (result != null) {
                    characterList.postValue(result)

                    *//*val characterItems = result.mapIndexed { _, character ->
                       *//**//* CharacterItem(
                            id = character.id, name = character.name, image = character.image
                        )*//**//*
                    }*//*

                }
                isLoading.postValue(false)
            }*/
        }
        //loadCharacterPaginated()
    }

    fun loadCharacterPaginated() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getCharacterList(1)
            if (!result.isNullOrEmpty() /*is Resource.Success*/) {
                //endReached.value = result.data?.info?.next == null
                val characterItems = result.mapIndexed { _, character ->
                    /*CharacterItem(
                        id = character.id, name = character.name, image = character.image
                    ) */
                }
                currentPage++
                //loadError.value = ""
                isLoading.value = false
                //characterList.value = characterList.value?.plus(characterItems)
            } else /*if (result is Resource.Error) */{
                //loadError.value = result.message!!
                isLoading.value = false
            }
        }
    }
}