package com.example.profiles.profiles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.profiles.databinding.ProfileItemBinding
import com.example.profiles.network.Profile

class ProfilesListAdapter: ListAdapter<Profile, ProfilesListAdapter.ProfilesViewHolder>(DiffCallback) {
    class ProfilesViewHolder(private var binding: ProfileItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            binding.itemName.text = profile.name
            binding.itemEmail.text = profile.email
            if(profile.isActive){
                binding.itemState.text = "Active"
            } else binding.itemState.text = "Not Active"
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Profile>() {
        override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ProfilesViewHolder {
        return ProfilesViewHolder(ProfileItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ProfilesViewHolder, position: Int) {
        val profile = getItem(position)
        holder.bind(profile)
    }
}