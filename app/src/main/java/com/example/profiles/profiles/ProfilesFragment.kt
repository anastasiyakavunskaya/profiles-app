package com.example.profiles.profiles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.profiles.R
import com.example.profiles.databinding.FragmentProfilesBinding
import com.example.profiles.network.Profile


class ProfilesFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProfilesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profiles, container, false)
        val activity = requireNotNull(this.activity)
        val viewModel: ProfilesViewModel =
            ViewModelProvider(this, ProfilesViewModel.Factory(activity.application))
                .get(ProfilesViewModel::class.java)

        binding.lifecycleOwner = this

        val adapter =
            ProfilesListAdapter(ProfileListener {
                this.findNavController().navigate(ProfilesFragmentDirections.actionProfilesFragmentToDetailsFragment(it))
            })

        val manager = LinearLayoutManager(activity)
        binding.recycler.layoutManager = manager
        binding.recycler.adapter = adapter

        viewModel.profiles.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }
}