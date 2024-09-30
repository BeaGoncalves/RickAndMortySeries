package com.example.rickandmortyseries.ui.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rickandmortyseries.databinding.FragmentErrorNetworkBinding

class NetworkErrorFragment : Fragment() {

    private lateinit var binding: FragmentErrorNetworkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentErrorNetworkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            val action = NetworkErrorFragmentDirections.actionErrorFragmentToCharacterListFragment()
            findNavController().navigate(action)
        }
    }
}