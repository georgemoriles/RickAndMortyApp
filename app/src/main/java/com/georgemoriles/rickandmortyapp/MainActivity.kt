package com.georgemoriles.rickandmortyapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.georgemoriles.rickandmortyapp.data.model.CharacterItem
import com.georgemoriles.rickandmortyapp.databinding.ActivityMainBinding
import com.georgemoriles.rickandmortyapp.domain.GetCharacterUseCase
import com.georgemoriles.rickandmortyapp.ui.adapter.CharactersListAdapter
import com.georgemoriles.rickandmortyapp.ui.view.CharacterDetailActivity
import com.georgemoriles.rickandmortyapp.ui.view.CharacterDetailFragment.Companion.CHARACTER_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //private val characterViewModel: CharacterViewModel by viewModels()

    private lateinit var characterAdapter: CharactersListAdapter
    var getCharacterUseCase = GetCharacterUseCase()

    private var currentPape = 1

    var characterList: List<CharacterItem> = listOf()
    private var characterFilterList: MutableList<CharacterItem> = mutableListOf()

    var isSearching: Boolean  = false

    var isLoading = false
    val visibleThreshold = 2 // Carga más cuando quedan 2 ítems visibles

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //characterViewModel.onCreate()
        initUI()
        /*characterViewModel.characterItem.observe(this, Observer { currentCharacter ->
            binding.rvCharacter.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = characterAdapter
            }
        })*/

        /* characterViewModel.isLoading.observe(this, Observer {
             binding.loading.isVisible = it
         })*/

    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                isSearching = true
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                isSearching = true
                if (!query.isNullOrEmpty()) {
                    searchByName(query)
                } else {
                    characterAdapter.updateList( characterList)
                    characterAdapter.notifyDataSetChanged()
                }
                return false
            }
        })

        CoroutineScope(Dispatchers.IO).launch {
            val result = getCharacterUseCase.invoke(currentPape)

            Log.d("jorge", "tamano" + result?.size.toString())
            if (result != null) {
                Log.i("JORGE", result.toString())
                runOnUiThread {
                    characterList = result
                    characterAdapter.updateList( result)
                    characterAdapter.notifyDataSetChanged()
                }
            }

        }

        characterAdapter = CharactersListAdapter() { characterId ->  navigateToDetail(characterId) }
        binding.rvCharacter.setHasFixedSize(true)
        binding.rvCharacter.layoutManager = GridLayoutManager(this, 2)
        binding.rvCharacter.adapter = characterAdapter

        binding.rvCharacter.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = binding.rvCharacter.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
                    // Llegamos al final, cargar más
                    loadMoreItems()
                    isLoading = true
                }

            }
        })

    }

    fun loadMoreItems() {
        CoroutineScope(Dispatchers.IO).launch {
            val result = getCharacterUseCase.invoke(currentPape)

            Log.d("jorge", "tamano" + result?.size.toString())
            if (result != null) {
                Log.i("JORGE", result.toString())
                runOnUiThread {
                    //characterList = result
                    characterAdapter.addItems( result)
                    currentPape++
                    isLoading = false
                }
            }

        }
    }

    private fun navigateToDetail(characterId: Int) {
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra(CHARACTER_ID, characterId)
        startActivity(intent)

    }

    private fun searchByName(query: String) {
        Log.d("jorge", "filtro")
        binding.loading.isVisible = true

        val listToSearch = if (isSearching) {
            characterList.toList()
        } else {
            characterFilterList
        }

        if(query.isEmpty()){
            characterFilterList = characterList.toMutableList()
        }

        val result = listToSearch.filter {
            it.name.contains(query.trim(), ignoreCase = true) || it.id.toString() == query.trim()
        }


        characterFilterList = result.toMutableList()
        characterAdapter.updateList(characterFilterList)
        binding.loading.isVisible = false

        binding.loading.isVisible = false
        isSearching = false

    }


}