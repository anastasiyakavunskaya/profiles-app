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


class DetailsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false)
        val activity = requireNotNull(this.activity)
        val profile = DetailsFragmentArgs.fromBundle(requireArguments()).profile
        binding.profile = profile

        val viewModel: DetailsViewModel =
            ViewModelProvider(this, DetailsViewModel.Factory(activity.application, profile))
                .get(DetailsViewModel::class.java)
        binding.viewModel = viewModel
        binding.registered.text = viewModel.formatDate()
        binding.location.text = viewModel.formatLocation()


        binding.email.setOnClickListener {
            val email = Uri.parse("mailto:"+Uri.encode(profile.email))
            val emailIntent = Intent(Intent.ACTION_SENDTO, email)
            requireContext().startActivity(
                Intent.createChooser(
                    emailIntent,"Send mail..."
                )
            )
        }

        val lat = profile.latitude.toString()
        val long = profile.longitude.toString()
        binding.location.setOnClickListener {
            val location = Uri.parse("geo:$lat,$long?q=$lat,$long")
            val mapIntent = Intent(Intent.ACTION_VIEW, location)
                requireContext().startActivity(
                    Intent.createChooser(
                        mapIntent,"Geo..."
                    )
                )
        }

        binding.phone.setOnClickListener {
            val phone =  Uri.parse("tel:"+Uri.encode(profile.phone))
            val phoneIntent = Intent(Intent.ACTION_DIAL, phone)
            requireContext().startActivity(
                Intent.createChooser(
                    phoneIntent,"Call..."
                )
            )
        }

        binding.eyeColorIndicator.setImageResource(viewModel.getEyeColor())
        binding.fruitIndicator.setImageResource(viewModel.getFruit())
        binding.lifecycleOwner = this

        val adapter =
            ProfilesListAdapter(ProfileListener {
                this.findNavController()
                    .navigate(DetailsFragmentDirections.actionDetailsFragmentSelf(it))
            })
        binding.recycler.adapter = adapter
        val manager = LinearLayoutManager(activity)
        binding.recycler.layoutManager = manager
        binding.lifecycleOwner = this
        viewModel.friends.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        return binding.root
    }

}