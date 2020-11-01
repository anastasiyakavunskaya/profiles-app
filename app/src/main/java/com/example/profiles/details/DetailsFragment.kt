package com.example.profiles.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.profiles.R
import com.example.profiles.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_details, container, false)

        val profile = DetailsFragmentArgs.fromBundle(requireArguments()).profile
        binding.profile = profile

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

        //binding.eyeColorIndicator.setBackgroundColor()
        return binding.root
    }

}