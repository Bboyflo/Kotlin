package com.ynov.kotlin.rickmorty.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.list.fragment.ListCharacterFragment
import com.ynov.kotlin.rickmorty.presentation.list.fragment.ListEpisodesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            setListFragment()
        }
        val navView: BottomNavigationView = nav_view
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_characters -> {
                setListFragment()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_episodes -> {
                setEpisodeFragment()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun setListFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_fragment_container, ListCharacterFragment())
            .commit()
    }

    private fun setEpisodeFragment(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_fragment_container, ListEpisodesFragment())
            .commit()
    }
}
