package com.ynov.kotlin.rickmorty.presentation.list.fragment

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
import com.ynov.kotlin.rickmorty.presentation.list.adapter.EpisodeListAdapter
import com.ynov.kotlin.rickmorty.presentation.list.viewmodel.EpisodeListViewModel
import kotlinx.android.synthetic.main.fragment_list_episodes.*

class ListEpisodesFragment : Fragment() {

    lateinit var episodeListAdapter: EpisodeListAdapter
    lateinit var episodeListViewModel: EpisodeListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_episodes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_list_episodes.layoutManager = LinearLayoutManager(context)
        fragment_list_episodes.adapter = EpisodeListAdapter()
        episodeListAdapter = fragment_list_episodes.adapter as EpisodeListAdapter

        episodeListViewModel = ViewModelProviders.of(this).get(EpisodeListViewModel::class.java)
        updateEpisodeList()

        catchErrorEpisodeList(view)

        swipeRefreshLayoutEpisode.setOnRefreshListener { refreshEpisodeList() }
    }
    private fun updateEpisodeList() =
        episodeListViewModel.episodeListLiveData.observe(this, Observer{
            swipeRefreshLayoutEpisode.isRefreshing = false
            episodeListAdapter.updateList(it)
        })

    private fun catchErrorEpisodeList(view: View) =
        episodeListViewModel.errorLiveData.observe(this, Observer{
            Snackbar.make(view, it.message.toString(), Snackbar.LENGTH_LONG).show()
        })

    private fun refreshEpisodeList() = episodeListViewModel.retrieveEpisodeList()
}
