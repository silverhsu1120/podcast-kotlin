package com.example.podcast.ui


import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.podcast.R
import com.example.podcast.databinding.FragmentMusicPlayerBinding
import com.example.podcast.viewmodel.CastDetailViewModel
import com.example.podcast.viewmodel.MusicPlayerViewModel
import kotlinx.android.synthetic.main.fragment_music_player.*
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass.
 */
class MusicPlayerFragment : Fragment(), SeekBar.OnSeekBarChangeListener {

    private lateinit var binding: FragmentMusicPlayerBinding

    private val sharedViewModel by lazy {
        ViewModelProvider(requireActivity())[CastDetailViewModel::class.java]
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MusicPlayerViewModel::class.java]
    }

    private val coroutineScope = CoroutineScope(Dispatchers.Main + Job())
    private val player = MediaPlayer()
    private var isSeeking = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_music_player, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.sharedViewModel = sharedViewModel
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt("position") ?: 0
        val tracks = sharedViewModel.tracks.value ?: emptyList()
        if (tracks.size > position) {
            val track = tracks[position]
            tv_title.text = track.title
            player.setDataSource(track.contentUrl)
            player.prepareAsync()
            player.setOnPreparedListener { mp ->
                seek_bar?.max = mp.duration
                seek_bar?.setOnSeekBarChangeListener(this)
                viewModel.isPlaying.value = true
            }
            player.setOnCompletionListener {
                viewModel.isPlaying.value = false
            }
        }

        viewModel.isPlaying.observe(viewLifecycleOwner, Observer { isPlaying ->
            if (isPlaying) { player.start() }
            else { player.pause() }
        })
    }

    override fun onResume() {
        super.onResume()

        coroutineScope.launch(Dispatchers.Main) {
            while (true) {
                if (!isSeeking) {
                    seek_bar.progress = player.currentPosition
                }
                delay(500)
            }
        }
    }

    override fun onPause() {
        super.onPause()

        coroutineScope.cancel()
        viewModel.isPlaying.value = false
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (isSeeking) {
            player.seekTo(seek_bar.progress)
        }
        viewModel.calculateElapsedTime(progress.toLong())
        seekBar?.max?.let { max ->
            val millis = (max - progress).toLong()
            viewModel.calculateRemainingTime(millis)
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        isSeeking = true
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        isSeeking = false
    }
}
