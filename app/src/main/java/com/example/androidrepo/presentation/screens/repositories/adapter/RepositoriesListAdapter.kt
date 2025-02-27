package com.example.androidrepo.presentation.screens.repositories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidrepo.databinding.RepositoriesListItemBinding
import com.example.androidrepo.domain.model.repositories.Items
import com.example.androidrepo.presentation.utils.addEllipsis
import com.example.androidrepo.presentation.utils.toTitle


class RepositoriesListAdapter(
    private val listRepositories: List<Items>,
    private val onItemClicked: (String, String) -> Unit
) :
    RecyclerView.Adapter<RepositoriesListAdapter.RepositoriesViewHolder>() {

    inner class RepositoriesViewHolder(private val binding: RepositoriesListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: Items) {
            binding.apply {
                tvRepositoryName.text = item.name.toTitle()
                tvRepoDesc.text = addEllipsis(item.description)
                tvForkNumbers.text = "${item.forksCount}"
                tvStarsNumbers.text = "${item.stargazersCount}"
                tvUsername.text = item.owner.login
                Glide.with(binding.root).load(item.owner.avatarUrl).into(ivOwner)
                itemView.setOnClickListener { onItemClicked(item.owner.login, item.name) }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RepositoriesViewHolder {
        return RepositoriesViewHolder(
            RepositoriesListItemBinding.inflate(
                LayoutInflater.from(
                    viewGroup.context
                ), viewGroup, false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: RepositoriesViewHolder, position: Int) {
        val repository = listRepositories[position]
        viewHolder.bindItem(repository)
    }

    override fun getItemCount() = listRepositories.size
}