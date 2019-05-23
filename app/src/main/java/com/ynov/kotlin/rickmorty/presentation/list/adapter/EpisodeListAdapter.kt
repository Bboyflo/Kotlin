package com.ynov.kotlin.rickmorty.presentation.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.model.RMEpisode
import kotlinx.android.synthetic.main.view_episode_list_item.view.*

class EpisodeListAdapter : RecyclerView.Adapter<EpisodeListAdapter.ViewHolder>() {

    private val episodeList : MutableList<RMEpisode> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_episode_list_item, parent, false))

    override fun getItemCount(): Int = episodeList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(episodeList[position])
    }

    fun updateList(newEpisodeList: List<RMEpisode>){
        episodeList.clear()
        episodeList.addAll(newEpisodeList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(rmEpisode: RMEpisode){
            itemView.id_name_episode.text = rmEpisode.name
            itemView.id_episode.text = rmEpisode.episode
        }
    }
}