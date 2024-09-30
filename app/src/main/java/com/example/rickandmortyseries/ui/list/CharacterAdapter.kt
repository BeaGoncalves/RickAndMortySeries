package com.example.rickandmortyseries.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.rickandmortyseries.common.translateToSpecie
import com.example.rickandmortyseries.common.ui.ImageCircleTransform
import com.example.rickandmortyseries.data.Character
import com.example.rickandmortyseries.databinding.ItemListBinding
import com.squareup.picasso.Picasso

/**
 * Adapter class for character list.
 */
internal class CharacterAdapter(val items: List<Character>, val listener: OnCharacterClickListener) :
    Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class CharacterViewHolder(private val binding: ItemListBinding) : ViewHolder(binding.root) {

        fun bind(character: Character) {
            with(binding) {
                textNameCharacter.text = character.name
                textSpeciesCharacter.text = character.species.translateToSpecie()

                Picasso.get()
                    .load(character.image)
                    .transform(ImageCircleTransform())
                    .into(imageView)

                cardContainerItem.setOnClickListener {
                    listener.onCharacterClick(character)
                }
            }
        }
    }
}

/**
 * Interface for character click.
 */
internal interface OnCharacterClickListener {
    fun onCharacterClick(character: Character)
}