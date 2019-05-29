package com.ynov.kotlin.rickmorty.presentation.list.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.detail.activity.DetailActivity
import com.ynov.kotlin.rickmorty.presentation.list.adapter.CharacterListAdapter
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.fragment_list_characters.*

class ListCharacterFragment : Fragment() {

    // TODO en private ça peut être pas mal pour ces variables
    lateinit var characterListAdapter: CharacterListAdapter
    lateinit var characterListViewModel: CharacterListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_characters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_list_characters.layoutManager = LinearLayoutManager(context)
        fragment_list_characters.adapter = CharacterListAdapter()
        characterListAdapter = fragment_list_characters.adapter as CharacterListAdapter

        characterListAdapter.onClick = { setDetailCharacterActivity(it) }

        characterListViewModel = ViewModelProviders.of(this).get(CharacterListViewModel::class.java)
        updateCharacterList()

        catchErrorCharacterList(view)

        swipeRefreshLayoutCharacter.setOnRefreshListener { refreshCharacterList() }
    }

    private fun setDetailCharacterActivity(id: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("idCharacter", id.toString())
        startActivity(intent)
    }

    private fun updateCharacterList() =
        characterListViewModel.characterListLiveData.observe(this, Observer{
            swipeRefreshLayoutCharacter.isRefreshing = false
            characterListAdapter.updateList(it)
        })

    private fun catchErrorCharacterList(view: View) =
        characterListViewModel.errorLiveData.observe(this, Observer{
            Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_LONG).show()
        })

    private fun refreshCharacterList() = characterListViewModel.retrieveCharacterList()
}