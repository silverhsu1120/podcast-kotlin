package com.example.podcast.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.podcast.R
import com.example.podcast.databinding.FragmentCastDetailBinding
import com.example.podcast.extension.navigateSafe
import com.example.podcast.viewmodel.CastDetailViewModel
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_cast_detail.*

/**
 * A simple [Fragment] subclass.
 */
class CastDetailFragment : Fragment() {

    private lateinit var binding: FragmentCastDetailBinding

    private val viewModel by lazy {
        ViewModelProvider(requireActivity())[CastDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cast_detail, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TrackListAdapter()
        adapter.onItemClick = { position ->
            val bundle = bundleOf("position" to position)
            findNavController().navigateSafe(R.id.action_castDetailFragment_to_musicPlayerFragment, bundle)
        }
        track_list.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        viewModel.getCastDetail()
    }
}
