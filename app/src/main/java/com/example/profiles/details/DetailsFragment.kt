package com.example.profiles.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.profiles.R
import com.example.profiles.databinding.FragmentDetailsBinding
import com.example.profiles.profiles.ProfileListener
import com.example.profiles.profiles.ProfilesFragmentDirections
import com.example.profiles.profiles.ProfilesListAdapter
import com.example.profiles.profiles.ProfilesViewModel
import androidx.lifecycle.Observer



class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false)
        val activity = requireNotNull(this.activity)

        val profile = DetailsFragmentArgs.fromBundle(requireArguments()).profile
        binding.profile = profile

        val viewModel: DetailsViewModel =
            ViewModelProvider(this, DetailsViewModel.Factory(activity.application, profile.friends))
                .get(DetailsViewModel::class.java)



        when(profile.eyeColor){
            "brown" -> binding.eyeColorIndicator.setBackgroundColor(resources.getColor(R.color.colorBrown))
            "green" -> binding.eyeColorIndicator.setBackgroundColor(resources.getColor(R.color.colorGreen))
            "blue" -> binding.eyeColorIndicator.setBackgroundColor(resources.getColor(R.color.colorBlue))
        }
        when(profile.favoriteFruit){
            "banana" -> binding.fruitIndicator.setImageDrawable(resources.getDrawable(R.drawable.bananas))
            "apple" -> binding.fruitIndicator.setImageDrawable(resources.getDrawable(R.drawable.apple))
            "strawberry" -> binding.fruitIndicator.setImageDrawable(resources.getDrawable(R.drawable.strawberry))
        }

        binding.lifecycleOwner = this

        val adapter =
            ProfilesListAdapter(ProfileListener {
                this.findNavController().navigate(DetailsFragmentDirections.actionDetailsFragmentSelf(it))
            })

        val manager = LinearLayoutManager(activity)
        binding.recycler.layoutManager = manager
        binding.recycler.adapter = adapter

        viewModel.friends.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        return binding.root
    }

}