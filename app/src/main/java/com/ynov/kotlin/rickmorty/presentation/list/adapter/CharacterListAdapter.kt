package com.ynov.kotlin.rickmorty.presentation.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.data.model.RMCharacter
import kotlinx.android.synthetic.main.view_character_list_item.view.*

class CharacterListAdapter : RecyclerView.Adapter<CharacterListAdapter.ViewHolder>() {

    lateinit var onClick:(Int) -> Unit
    private val characterList : MutableList<RMCharacter> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_character_list_item, parent, false))

    override fun getItemCount(): Int = characterList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characterList[position], onClick)
    }

    fun updateList(newCharacterList: List<RMCharacter>){
        characterList.clear()
        characterList.addAll(newCharacterList)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(rmCharacter: RMCharacter, onClick:(Int) -> Unit){
            itemView.id_name.text = rmCharacter.name
            itemView.id_gender.text = rmCharacter.gender
            Picasso.get().load(rmCharacter.image).placeholder(R.drawable.ic_placeholder_image).into(itemView.id_image)
            itemView.setOnClickListener{ onClick(rmCharacter.id) }
        }
    }
}