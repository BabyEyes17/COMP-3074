package ca.gbc.treasurely.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.Toast

fun saveQrToGallery(context: Context, bitmap: Bitmap, name: String) {
    val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
    val values = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, "QR_$name.jpg")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.WIDTH, bitmap.width)
        put(MediaStore.Images.Media.HEIGHT, bitmap.height)
    }

    val uri = context.contentResolver.insert(collection, values)

    uri?.let {
        context.contentResolver.openOutputStream(it)?.use { out ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        }
        Toast.makeText(context, "QR Code saved to gallery!", Toast.LENGTH_SHORT).show()
    }
}
