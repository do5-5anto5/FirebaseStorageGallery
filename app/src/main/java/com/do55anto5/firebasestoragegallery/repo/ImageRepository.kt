package com.do55anto5.firebasestoragegallery.repo

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage

open class ImageRepository {
    fun uploadImageToFirebase(imageUri: Uri?, context: Context, onComplete: () -> Unit) {
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        val imageRef = storageRef.child("images/${imageUri!!.lastPathSegment}")

        val uploadTask = imageUri.let { imageRef.putFile(it) }
        uploadTask.addOnSuccessListener {
            onComplete()
            Toast.makeText(
                context,
                "Image Upload complete",
                Toast.LENGTH_SHORT
            ).show()
        }.addOnFailureListener {
            onComplete()
            Toast.makeText(
                context,
                "Image Upload failed",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}