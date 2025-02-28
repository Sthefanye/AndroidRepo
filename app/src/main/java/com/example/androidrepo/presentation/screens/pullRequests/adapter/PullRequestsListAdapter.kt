package com.example.androidrepo.presentation.screens.pullRequests.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidrepo.databinding.PullRequestListItemBinding
import com.example.androidrepo.domain.model.PullRequests
import com.example.androidrepo.utils.addEllipsis

class PullRequestsListAdapter (private val listPullRequests: List<PullRequests>) :
    RecyclerView.Adapter<PullRequestsListAdapter.PullRequestViewHolder>() {

    inner class PullRequestViewHolder(private val binding: PullRequestListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(pullRequest: PullRequests) {
            binding.apply {
                tvPullRequestName.text = pullRequest.title
                tvPullRequestDescription.text = addEllipsis(pullRequest.body ?: "")
                tvUsername.text = pullRequest.user.login
                Glide.with(binding.root).load(pullRequest.user.avatarUrl).into(ivOwnerAvatar)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): PullRequestViewHolder {
        return PullRequestViewHolder(
            PullRequestListItemBinding.inflate(
                LayoutInflater.from(
                    viewGroup.context
                ), viewGroup, false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: PullRequestViewHolder, position: Int) {
        val pullRequestsList = listPullRequests[position]
        viewHolder.bindItem(pullRequestsList)
    }

    override fun getItemCount() = listPullRequests.size
}
