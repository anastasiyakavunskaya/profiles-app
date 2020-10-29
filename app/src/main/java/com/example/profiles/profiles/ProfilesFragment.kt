package com.example.profiles.profiles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.profiles.R
import com.example.profiles.databinding.FragmentProfilesBinding


class ProfilesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProfilesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profiles, container, false)


        return binding.root
    }
}