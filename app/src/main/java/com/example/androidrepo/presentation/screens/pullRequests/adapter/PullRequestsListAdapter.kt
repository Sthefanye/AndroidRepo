package com.example.androidrepo.presentation.screens.pullRequests.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidrepo.R
import com.example.androidrepo.databinding.PullRequestListItemBinding
import com.example.androidrepo.domain.model.PullRequests
import com.example.androidrepo.utils.addEllipsis
import com.example.androidrepo.utils.formatPRNumber

class PullRequestsListAdapter (private val listPullRequests: List<PullRequests>,
                               private val onItemClicked: (String) -> Unit) :
    RecyclerView.Adapter<PullRequestsListAdapter.PullRequestViewHolder>() {

    inner class PullRequestViewHolder(private val binding: PullRequestListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(pullRequest: PullRequests) {
            binding.apply {
                tvPullRequestNumber.text = formatPRNumber(pullRequest.number)
                tvPullRequestName.text =  addEllipsis(pullRequest.title, 25)
                tvPullRequestDescription.text = addEllipsis(pullRequest.body ?: "", 80)
                tvUsername.text = pullRequest.user.login
                Glide.with(binding.root).load(pullRequest.user.avatarUrl).into(ivOwnerAvatar)
                itemView.setOnClickListener { onItemClicked(pullRequest.htmlUrl) }
                mcPullRequest.startAnimation(
                    AnimationUtils.loadAnimation(
                        binding.root.context,
                        R.anim.anim_list
                    )
                )
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
