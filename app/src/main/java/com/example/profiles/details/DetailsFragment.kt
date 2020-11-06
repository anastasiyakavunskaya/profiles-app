package com.example.profiles.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.profiles.R
import com.example.profiles.databinding.FragmentDetailsBinding
import com.example.profiles.profiles.ProfileListener
import com.example.profiles.profiles.ProfilesListAdapter
import com.google.android.material.snackbar.Snackbar


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
            ViewModelProvider(this, DetailsViewModel.Factory(activity.application, profile))
                .get(DetailsViewModel::class.java)

        binding.viewModel = viewModel
        binding.email.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO)
            emailIntent.data = Uri.parse("mailto:"+Uri.encode(profile.email))
            requireContext().startActivity(
                Intent.createChooser(
                    emailIntent,"Send mail..."
                )
            )
        }
        binding.coordinates.setOnClickListener {
                val mapIntent = Intent(Intent.ACTION_VIEW)
                mapIntent.data = Uri.parse("geo:"+
                        Uri.encode(profile.latitude.toString()+", "+profile.longitude.toString()))
                requireContext().startActivity(
                    Intent.createChooser(
                        mapIntent,"Geo..."
                    )
                )
        }
        binding.phone.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL)
            callIntent.data = Uri.parse("tel:"+Uri.encode(profile.phone))
            requireContext().startActivity(
                Intent.createChooser(
                    callIntent,"Call..."
                )
            )
        }


        when(profile.eyeColor){
            "brown" -> binding.eyeColorIndicator.setImageResource(R.drawable.brown_eyes)
            "green" -> binding.eyeColorIndicator.setImageResource(R.drawable.green_eyes)
            "blue" -> binding.eyeColorIndicator.setImageResource(R.drawable.blue_eyes)
        }
        when(profile.favoriteFruit){
            "banana" -> binding.fruitIndicator.setImageResource(R.drawable.bananas)
            "apple" -> binding.fruitIndicator.setImageResource(R.drawable.apple)
            "strawberry" -> binding.fruitIndicator.setImageResource(R.drawable.strawberry)
        }

        binding.registered.text = viewModel.formatDate()
        binding.coordinates.text = viewModel.formatCoordinates()

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