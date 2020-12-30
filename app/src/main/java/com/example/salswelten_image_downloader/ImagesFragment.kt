package com.example.salswelten_image_downloader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.example.salswelten_image_downloader.adapter.ImageItem
import com.example.salswelten_image_downloader.adapter.ImageItemAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_images.*
import java.io.File
import java.nio.file.Paths

class ImagesFragment : Fragment (), ImageItemDownloadListener.ImageItemDownloadConsumer {

    private val imageItemAdapter = ImageItemAdapter()
    private val imageItems = listOf(
        ImageItem("CP2077", "https://i2.wp.com/metro.co.uk/wp-content/uploads/2019/06/keanu-reeves-cyberpunk-2077-1781.jpg?quality=90&strip=all&zoom=1&resize=480%2C240&ssl=1"),
        ImageItem("Control", "https://img.youtube.com/vi/BnQwE0ug_Q0/maxresdefault.jpg"))
    private lateinit var imageDownloadService: ImageDownloadService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_images, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        imageDownloadService = ImageDownloadService(requireContext())
        imagesRecyclerView.adapter = imageItemAdapter
        imageItemAdapter.submitItems(imageItems)
        imageItems.forEach {
            imageDownloadService.downloadImage(it, ImageItemDownloadListener(it, this))
        }
    }

    override fun accept(imageItem: ImageItem, percentageProgress: Int) {
        requireActivity().runOnUiThread {
            val posotion = imageItemAdapter.items.indexOf(imageItem)
            val view = imagesRecyclerView[posotion]
            view.findViewById<TextView>(R.id.downloadProgressTextView).text =  "$percentageProgress %"

            if(percentageProgress == 100) {
                val filePath = Paths.get(this.requireContext().filesDir.absolutePath, imageItem.fileName).toAbsolutePath().toString()
                Picasso.get().load(File(filePath)).into(view.findViewById<ImageView>(R.id.imageItemView))
            }
        }
    }
}