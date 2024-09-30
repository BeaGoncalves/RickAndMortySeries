package com.example.rickandmortyseries.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyseries.data.Character
import com.example.rickandmortyseries.data.ErrorType
import com.example.rickandmortyseries.databinding.CharacterListFragmentBinding
import com.example.rickandmortyseries.viewmodel.CharacterViewModel
import com.example.rickandmortyseries.viewmodel.CharacterViewState

internal class CharacterListFragment : Fragment(), OnCharacterClickListener {

    private lateinit var binding: CharacterListFragmentBinding

    private val viewModel by viewModels<CharacterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharacterListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupListener()
    }

    override fun onCharacterClick(character: Character) {
        val action =
            CharacterListFragmentDirections.actionCharacterListFragmentToDetailsFragment(character)
        findNavController().navigate(action)
    }

    private fun setupViewModel() {

        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CharacterViewState.Success -> onSuccess(state.characterList)
                is CharacterViewState.Error -> onError(state.errorType)
            }
        }
    }

    private fun setupListener() {

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.filterByName(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.filterByName(newText.toString())
                return true
            }
        })

    }

    private fun onSuccess(characterList: List<Character>) {
        val adapter = CharacterAdapter(characterList, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun onError(errorType: ErrorType) {
        when (errorType) {
            ErrorType.NETWORK_ERROR -> findNavController().navigate(
                CharacterListFragmentDirections
                    .actionCharacterListFragmentToErrorFragment()
            )

            else -> { // do nothing
            }
        }
    }
}