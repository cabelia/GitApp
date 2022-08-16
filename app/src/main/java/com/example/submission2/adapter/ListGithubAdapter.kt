package com.example.submission2.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.submission2.api.Item
import com.example.submission2.databinding.RowUserGithubBinding

class ListGithubAdapter(private val userGithub: List<Item>) :
    RecyclerView.Adapter<ListGithubAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Item)
    }

    class ListViewHolder(val binding: RowUserGithubBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding =
            RowUserGithubBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = userGithub.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.apply {
            imgAvatar.load(userGithub[position].avatarUrl)
            tvUsername.text = userGithub[position].login
            tvNama.text = userGithub[position].htmlUrl

            root.setOnClickListener { onItemClickCallback.onItemClicked(userGithub[position]) }
        }
    }
}

