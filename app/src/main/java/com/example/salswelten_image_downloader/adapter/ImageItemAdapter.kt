package com.example.salswelten_image_downloader.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.salswelten_image_downloader.R
import io.spotar.tour.ui.base.view.recyclerview.RecyclerViewAdapter

class ImageItemAdapter: RecyclerViewAdapter<ImageItem>() {
    override fun onBindItemView(view: View, position: Int) {
        val imageItemNameTextView = view.findViewById<TextView>(R.id.imageItemName)
        imageItemNameTextView.text = items[position].fileName
    }

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): View {
        return LayoutInflater.from(parent.context).inflate(R.layout.view_image_items, parent, false)
    }
}