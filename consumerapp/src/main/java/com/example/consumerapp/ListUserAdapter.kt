package com.example.consumerapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.consumerapp.databinding.RowUserGithubBinding


class ListUserAdapter(private val githubDetail: List<GithubDetail>) :
    RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: GithubDetail)
    }

    class ListViewHolder(val binding: RowUserGithubBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding =
            RowUserGithubBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = githubDetail.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.binding.apply {
            imgAvatar.load(githubDetail[position].avatarUrl)
            tvUsername.text = githubDetail[position].login
            tvNama.text = githubDetail[position].htmlUrl

            root.setOnClickListener { onItemClickCallback.onItemClicked(githubDetail[holder.adapterPosition]) }
        }
    }
}