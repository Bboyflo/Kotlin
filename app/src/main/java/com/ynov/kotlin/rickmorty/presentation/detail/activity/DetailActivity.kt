package com.ynov.kotlin.rickmorty.presentation.detail.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ynov.kotlin.rickmorty.R
import com.ynov.kotlin.rickmorty.presentation.detail.fragment.DetailFragment

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        val bundle = Bundle()
        bundle.putString("idCharacter", intent.getStringExtra("idCharacter"))
        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.detail_activity_fragment_container, detailFragment)
            .commit()
    }
}
