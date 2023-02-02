package com.cookandroid.myapplication

import android.content.Context
import android.graphics.Color
import android.media.MediaMetadataRetriever
import androidx.appcompat.app.AlertDialog
import com.google.android.material.color.MaterialColors
import java.util.concurrent.TimeUnit
import kotlin.system.exitProcess

data class Music(val id:String, val title:String, val album:String, val artist:String,
                 val duration:Long=0, val path: String, val artUri:String)

class Playlist{
    lateinit var name: String
    lateinit var playlist: ArrayList<Music>
    lateinit var createdBy: String
    lateinit var createdOn: String
}

class MusicPlaylist{
    var ref: ArrayList<Playlist> = ArrayList()
}

fun formatDuration(duration: Long):String{
    val minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS)
    val seconds = (TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS) -
            minutes* TimeUnit.SECONDS.convert(1, TimeUnit.MINUTES))
    return String.format("%2d:%2d", minutes, seconds)
}
fun getImgArt(path: String): ByteArray?{
    val retriever = MediaMetadataRetriever()
    retriever.setDataSource(path)
    return retriever.embeddedPicture
}
fun setSongPosition(increment: Boolean){
    if(!PlayMusicActivity.repeat){
        if(increment)
        {
            if(PlayMusicActivity.musicListPA.size -1 ==PlayMusicActivity.songPosition)
                PlayMusicActivity.songPosition = 0
            else ++PlayMusicActivity.songPosition
        }else{
            if(0 == PlayMusicActivity.songPosition)
                PlayMusicActivity.songPosition = PlayMusicActivity.musicListPA.size-1
            else --PlayMusicActivity.songPosition
        }
    }
}

