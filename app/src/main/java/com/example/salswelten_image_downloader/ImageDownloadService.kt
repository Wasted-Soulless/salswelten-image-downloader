package com.example.salswelten_image_downloader

import android.content.Context
import android.util.Log
import com.example.salswelten_image_downloader.adapter.ImageItem
import io.spotar.tour.common.network.ConnectionErrorNotifier
import io.spotar.tour.commonandroid.file.DeviceFileService
import io.spotar.tour.commonandroid.file.FileDownloadService

class ImageDownloadService (private val context: Context){
    private val deviceFileService: DeviceFileService
    private val fileDownloadService: FileDownloadService

    init {
        deviceFileService = DeviceFileService(context)
        fileDownloadService = FileDownloadService(deviceFileService, object : ConnectionErrorNotifier {
            override fun notify(errorMessage: String) {
                Log.e("ImageDownloadService", errorMessage)
            }
        })
    }

    fun downloadImage(imageItem: ImageItem, imageItemDownloadListener: ImageItemDownloadListener){
        fileDownloadService.downloadFile(imageItem.fileName, imageItem.url, imageItemDownloadListener)
    }
}