package com.example.androidrepo.presentation.screens.repositories.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidrepo.databinding.RepositoriesListItemBinding
import com.example.androidrepo.domain.model.repositories.Items
import com.example.androidrepo.presentation.utils.addEllipsis
import com.example.androidrepo.presentation.utils.toTitle
import com.squareup.picasso.Picasso

class RepositoriesListAdapter(
    private val listRepositories: List<Items>,
    private val onItemClicked: () -> Unit
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
                Picasso.get().load(item.owner.avatarUrl).into(ivOwner);
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
        viewHolder.itemView.setOnClickListener { onItemClicked() }
        viewHolder.bindItem(repository)
    }

    override fun getItemCount() = listRepositories.size
}