package com.example.profiles.profiles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
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

        val viewModel = ProfilesViewModel()
        viewModel.getProfiles()

        binding.lifecycleOwner = this

        val adapter =
            ProfilesListAdapter(ProfileListener {
                this.findNavController().navigate(R.id.action_profilesFragment_to_detailsFragment)
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