package com.example.salswelten_image_downloader

import com.example.salswelten_image_downloader.adapter.ImageItem
import io.spotar.tour.api.download.progress.DownloadProgressListener

class ImageItemDownloadListener(private val imageItem:ImageItem, private val consumer: ImageItemDownloadConsumer):DownloadProgressListener {
    override fun onProgress(percentageProgress: Int) {
        consumer.accept(imageItem, percentageProgress)
    }
    interface ImageItemDownloadConsumer{
        fun accept(imageItem: ImageItem, percentageProgress: Int)
    }
}