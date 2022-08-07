package com.example.pdf_downloader

import android.util.Log
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.flutterframework/test";
    private lateinit var channel: MethodChannel

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        channel = MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
        channel.setMethodCallHandler { call, result ->
            if (call.method == "download") {
                val pdfTitle = call.argument<String>("title")
                val pdfUrl = call.argument<String>("pdf")
                Log.d("TAG", pdfTitle.toString());
                Log.d("TAG", pdfUrl.toString());

                DownloadHelper.downloadPDF(this, pdfTitle, pdfUrl);
            }
        }
    }
}
