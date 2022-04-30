package com.example.catapp

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.catapp.databinding.ActivityMainBinding
import com.example.catapp.repository.Repository

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var biding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(biding.root)
        supportActionBar?.hide()

        biding.btnCatSearch.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        if (view.id == R.id.btnCatSearch) {

            val progressBar = biding.pbLoading

            val repository = Repository()
            val viewModelFactory = MainViewModelFactory(repository)
            viewModel = ViewModelProvider(this, viewModelFactory)
                .get(MainViewModel::class.java)
            viewModel.getImage()
            Glide.with(biding.root.context)
                .load(BitmapFactory.decodeStream(viewModel.myResponse.value?.byteStream()))
                .into(biding.imgCat)
        }
    }
}