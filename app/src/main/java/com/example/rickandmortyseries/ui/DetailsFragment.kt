package com.example.rickandmortyseries.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.rickandmortyseries.R
import com.example.rickandmortyseries.common.translateToGender
import com.example.rickandmortyseries.common.translateToOrigin
import com.example.rickandmortyseries.common.translateToSpecie
import com.example.rickandmortyseries.common.translateToStatus
import com.example.rickandmortyseries.data.Character
import com.example.rickandmortyseries.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

internal class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        val character = args.character
        setupCardProfile(character)
    }

    private fun setupCardProfile(character: Character) {
        with(binding) {
            textName.text = character.name
            textSpecie.text = character.species.translateToSpecie()
            textOrigin.text = character.origin.name.translateToOrigin()
            textLocation.text = character.location.name
            textStatus.text = character.status.translateToStatus()
            Picasso.get().load(character.image).into(imageCharacter)
            textGender.text = character.gender.translateToGender()
            textEpisode.text =
                getString(R.string.details_description_episodes, character.episode.size)
        }
    }

}