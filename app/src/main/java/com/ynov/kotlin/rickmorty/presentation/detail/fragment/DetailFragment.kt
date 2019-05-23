package com.ynov.kotlin.rickmorty.presentation.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.detail.viewmodel.CharacterDetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    lateinit var characterDetailViewModel: CharacterDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("idCharacter")?.let{ id ->
            characterDetailViewModel = CharacterDetailViewModel(id)
        }
        characterDetailViewModel.characterDetailLiveData.observe(this, Observer{
            id_name_detail.text = it.name
            id_gender_detail.text = it.gender
            Picasso.get().load(it.image).placeholder(R.drawable.ic_placeholder_image).into(id_image_detail)
            id_species_detail.text = it.species
            id_origin_detail.text = it.origin
            id_location_detail.text = it.location
        })

        characterDetailViewModel.errorLiveData.observe(this, Observer{
            Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_LONG).show()
        })
    }
}
