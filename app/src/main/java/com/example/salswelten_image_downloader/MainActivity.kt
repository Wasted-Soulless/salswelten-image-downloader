package com.example.salswelten_image_downloader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Picasso.setSingletonInstance(Picasso.Builder(this).build())

        supportFragmentManager.beginTransaction().add(R.id.main_container, ImagesFragment(), "ImagesFragment").commit()

    }
}