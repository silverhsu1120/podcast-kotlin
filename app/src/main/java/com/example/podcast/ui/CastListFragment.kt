package com.example.podcast.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.podcast.R
import com.example.podcast.databinding.FragmentCastListBinding
import com.example.podcast.extension.navigateSafe
import com.example.podcast.viewmodel.CastListViewModel
import kotlinx.android.synthetic.main.fragment_cast_list.*

/**
 * A simple [Fragment] subclass.
 */
class CastListFragment : Fragment() {

    private lateinit var binding: FragmentCastListBinding

    private val viewModel by lazy {
        ViewModelProvider(this)[CastListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cast_list, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.casts.observe(viewLifecycleOwner, Observer { casts ->
            val adapter = CastListAdapter(casts)
            adapter.onItemClick = {
                findNavController().navigateSafe(R.id.action_castListFragment_to_castDetailFragment)
            }
            cast_list.adapter = adapter
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getCasts()
    }
}
