package com.lab.android.media

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView

class PlayVideoActivity : AppCompatActivity() {

    var simpleVideoView: VideoView? = null
    var mediaController: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_video)

        simpleVideoView = findViewById<View>(R.id.simpleVideoView) as VideoView

        if (mediaController == null) {
            mediaController = MediaController(this)
            mediaController!!.setAnchorView(this.simpleVideoView)
        }

        simpleVideoView!!.setMediaController(mediaController)

        simpleVideoView!!.setVideoURI(
            Uri.parse("android.resource://" + packageName + "/" + R.raw.seals_video)
        )

        simpleVideoView!!.requestFocus()
        simpleVideoView!!.start()

        simpleVideoView!!.setOnErrorListener { _, _, _ ->
            Toast.makeText(applicationContext, "Error occurred!", Toast.LENGTH_LONG).show()
            false
        }
    }
}