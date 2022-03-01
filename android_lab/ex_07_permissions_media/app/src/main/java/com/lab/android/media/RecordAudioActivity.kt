package com.lab.android.media

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.app.ActivityCompat

class RecordAudioActivity : AppCompatActivity() {

    lateinit var mediaRecorder: MediaRecorder
    lateinit var mediaPlayer: MediaPlayer
    var audioDirPath: String? = null
    var audioFilePath: String? = null
    var audioFileName: String = "audio_filename1" + ".3gp"
    var wasStopped = false

    private val REQUEST_AUDIO_PERMISSION_CODE: Int = 111

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_audio)

        val startRecButton: Button = findViewById(R.id.startRecButton)
        val stopRecButton: Button = findViewById(R.id.stopRecButton)
        val playRecButton: Button = findViewById(R.id.playRecButton)
        val stopPlayButton: Button = findViewById(R.id.stopPlayButton)

        val seekBar: SeekBar = findViewById(R.id.audioSeekBar)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        val handler = Handler(Looper.getMainLooper())
        val updateThread: Runnable = object : Runnable {
            override fun run() {
                seekBar.progress = mediaPlayer.currentPosition
                handler.postDelayed(this, 100)
            }
        }

        mediaRecorder = MediaRecorder()
        audioDirPath = filesDir.toString()
        audioFilePath = "$audioDirPath/$audioFileName"

        stopRecButton.isEnabled = false
        playRecButton.isEnabled = false
        stopPlayButton.isEnabled = false

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ), REQUEST_AUDIO_PERMISSION_CODE
            )
            startRecButton.isEnabled = true
        }

        startRecButton.setOnClickListener {
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            mediaRecorder.setOutputFile(audioFilePath)
            mediaRecorder.prepare()
            mediaRecorder.start()
            stopRecButton.isEnabled = true
            startRecButton.isEnabled = false
            playRecButton.isEnabled = false
        }

        stopRecButton.setOnClickListener {
            mediaRecorder.stop()
            startRecButton.isEnabled = true
            stopRecButton.isEnabled = false
            playRecButton.isEnabled = true
        }

        playRecButton.setOnClickListener {
            if (!wasStopped) {
                mediaPlayer = MediaPlayer()
                mediaPlayer.setDataSource(audioFilePath)
                mediaPlayer.prepare()
                mediaPlayer.start()
                playRecButton.isEnabled = false
                stopPlayButton.isEnabled = true
                seekBar.max = mediaPlayer.duration
                handler.postDelayed(updateThread, 100)
            }
            else{
                mediaPlayer.start()
                handler.postDelayed(updateThread, 100)
                playRecButton.isEnabled = false
                stopPlayButton.isEnabled = true
            }
        }

        stopPlayButton.setOnClickListener {
            mediaPlayer.pause()
            playRecButton.isEnabled = true
            stopPlayButton.isEnabled = false
            wasStopped = true
            handler.removeCallbacks(updateThread)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_AUDIO_PERMISSION_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            val startRecButton: Button = findViewById(R.id.startRecButton)
            startRecButton.isEnabled = true
        }
    }

}