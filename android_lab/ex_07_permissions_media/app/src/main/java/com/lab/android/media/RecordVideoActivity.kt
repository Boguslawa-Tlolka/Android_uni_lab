package com.lab.android.media

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

class RecordVideoActivity : AppCompatActivity() {

    private val REQUEST_CAMERA_PERMISSION_CODE: Int = 11
    var mediaController: MediaController? = null
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var videoView: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_video)

        val recButton: Button = findViewById(R.id.recButton)

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION_CODE)
            if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA))
                recButton.isEnabled = true
        }

        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                videoView = findViewById(R.id.recordedVideoView)
                if (mediaController == null) {
                    mediaController = MediaController(this)
                    mediaController!!.setAnchorView(videoView)
                }

                videoView.setMediaController(mediaController)

                val videoUri: Uri? = result.data?.data
                videoView.setVideoURI(videoUri)

                videoView.requestFocus()
                videoView.start()

                videoView.setOnErrorListener { _, _, _ ->
                    Toast.makeText(applicationContext, "Error occurred!", Toast.LENGTH_LONG).show()
                    false
                }

            }
        }

        recButton.setOnClickListener {
            Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
                takeVideoIntent.resolveActivity(packageManager)?.also {
                    activityResultLauncher.launch(takeVideoIntent)
                }
            }
        }
    }
}