package com.example.profiles.profiles

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.profiles.R
import com.example.profiles.databinding.FragmentProfilesBinding
import com.google.android.material.snackbar.Snackbar


class ProfilesFragment : Fragment() {

    private lateinit var viewModel: ProfilesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentProfilesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profiles, container, false)
        viewModel =
            ViewModelProvider(this, ProfilesViewModel.Factory(requireNotNull(this.activity).application))
                .get(ProfilesViewModel::class.java)

        binding.lifecycleOwner = this

        val adapter =
            ProfilesListAdapter(ProfileListener {
                this.findNavController().navigate(ProfilesFragmentDirections.actionProfilesFragmentToDetailsFragment(it))
            })

        val manager = LinearLayoutManager(activity)
        binding.recycler.layoutManager = manager
        binding.recycler.adapter = adapter
        setHasOptionsMenu(true)
        viewModel.profiles.observe(viewLifecycleOwner, Observer {
            if(it.isEmpty()){
                Snackbar.make(requireView(),"Download the list of profiles with the button in the toolbar.", Snackbar.LENGTH_LONG).show()
            }
            adapter.submitList(it)
        })
        return binding.root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val cm = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return if(isConnected){
            (viewModel.refreshProfiles()
                    || super.onOptionsItemSelected(item))
        } else {
            Snackbar.make(requireView(),"No network connection. Check your Internet connection and try again.", Snackbar.LENGTH_LONG).show()
            false
        }
    }
}