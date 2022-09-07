package com.example.pdf_downloader;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;

public class DownloadHelper {
    public static void downloadPDF(Context context, String title, String pdfUrl) {
        String appName = context.getApplicationInfo().loadLabel(context.getPackageManager()).toString();

        try {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(pdfUrl));
            request.setDescription(appName);
            request.setTitle(title);
            // in order for this if to run, you must use the android 3.2 to compile your app
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            }

            if (Build.VERSION.SDK_INT <= 17) {
                request.setDestinationInExternalFilesDir(context, "Documents", "MyFolder/" + title + ".pdf");
            } else {
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "MyFolder/" + title + ".pdf");
            }

            // get download service and enqueue file
            DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(request);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
