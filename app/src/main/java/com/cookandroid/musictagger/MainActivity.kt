package com.cookandroid.musictagger

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.SeekBar

class MainActivity : AppCompatActivity() {
    lateinit var playButton:ImageButton
    lateinit var seek_bar:SeekBar
    lateinit var runnable:Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playButton=findViewById<ImageButton>(R.id.play_btn)
        seek_bar=findViewById<SeekBar>(R.id.seek_bar)

        val mediaplayer = MediaPlayer.create(this,R.raw.music)

        seek_bar.progress = 0
        seek_bar.max = mediaplayer.duration

        playButton.setOnClickListener {
            if(!mediaplayer.isPlaying){
                mediaplayer.start()
                playButton.setImageResource(R.drawable.ic_baseline_pause_24)
            }else{
                mediaplayer.pause()
                playButton.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            }
        }
        seek_bar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if(changed){
                    mediaplayer.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }

        })
        runnable = Runnable{
            seek_bar.progress=mediaplayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        mediaplayer.setOnCompletionListener {
            playButton.setImageResource(R.drawable.ic_baseline_play_arrow_24)
            seek_bar.progress=0
        }
    }
}