package com.example.androidrepo.data.mappers

import com.example.androidrepo.data.model.HeadResponse
import com.example.androidrepo.data.model.PullRequestsResponse
import com.example.androidrepo.data.model.RepoResponse
import com.example.androidrepo.data.model.UserResponse
import com.example.androidrepo.domain.model.Head
import com.example.androidrepo.domain.model.PullRequests
import com.example.androidrepo.domain.model.Repo
import com.example.androidrepo.domain.model.User

fun PullRequestsResponse.toDomain(): PullRequests {
    return PullRequests(
        url = url,
        id = id,
        nodeId = nodeId,
        htmlUrl = htmlUrl,
        diffUrl = diffUrl,
        patchUrl = patchUrl,
        issueUrl = issueUrl,
        number = number,
        state = state,
        locked = locked,
        title = title,
        user = user.toUser(),
        body = body,
        createdAt = createdAt,
        updatedAt = updatedAt,
        closedAt = closedAt,
        mergedAt = mergedAt,
        mergeCommitSha = mergeCommitSha,
        assignee = assignee?.toAssignee(),
        assignees = assignees?.map { it.toUser() },
        requestedReviewers = requestedReviewers?.map { it.toUser() },
        requestedTeams = requestedTeams?.map { it.toUser() },
        labels = labels?.map { it.toLabels() },
        milestone = milestone,
        draft = draft,
        commitsUrl = commitsUrl,
        reviewCommentsUrl = reviewCommentsUrl,
        reviewCommentUrl = reviewCommentUrl,
        commentsUrl = commentsUrl,
        statusesUrl = statusesUrl,
        head = head?.toHead(),
        links = links?.toLinks(),
        authorAssociation = authorAssociation,
        autoMerge = autoMerge,
        activeLockReason = activeLockReason
    )
}

private fun UserResponse.toUser(): User {
    return User(
        login = login,
        id = id,
        nodeId = nodeId,
        avatarUrl = avatarUrl,
        gravatarId = gravatarId,
        url = url,
        htmlUrl = htmlUrl,
        followersUrl = followersUrl,
        followingUrl = followingUrl,
        gistsUrl = gistsUrl,
        starredUrl = starredUrl,
        subscriptionsUrl = subscriptionsUrl,
        organizationsUrl = organizationsUrl,
        reposUrl = reposUrl,
        eventsUrl = eventsUrl,
        receivedEventsUrl = receivedEventsUrl,
        type = type,
        userViewType = userViewType,
        siteAdmin = siteAdmin
    )
}

private fun PullRequestsResponse.AssigneeResponse.toAssignee(): PullRequests.Assignee {
    return PullRequests.Assignee(
        login = login,
        id = id,
        nodeId = nodeId,
        avatarUrl = avatarUrl,
        gravatarId = gravatarId,
        url = url,
        htmlUrl = htmlUrl,
        followersUrl = followersUrl,
        followingUrl = followingUrl,
        gistsUrl = gistsUrl,
        starredUrl = starredUrl,
        subscriptionsUrl = subscriptionsUrl,
        organizationsUrl = organizationsUrl,
        reposUrl = reposUrl,
        eventsUrl = eventsUrl,
        receivedEventsUrl = receivedEventsUrl,
        type = type,
        userViewType = userViewType,
        siteAdmin = siteAdmin
    )
}

private fun PullRequestsResponse.LabelsResponse.toLabels(): PullRequests.Labels {
    return PullRequests.Labels(
        id = id,
        nodeId = nodeId,
        url = url,
        name = name,
        color = color,
        default = default,
        description = description
    )
}

private fun HeadResponse.toHead(): Head {
    return Head(
        label = label,
        ref = ref,
        sha = sha,
        user = user.toUser(),
        repo = repo.toRepo()
    )
}

private fun RepoResponse.toRepo(): Repo {
    return Repo(
        id = id,
        nodeId = nodeId,
        name = name,
        fullName = fullName,
        private = private,
        owner = owner.toOwner(),
        htmlUrl = htmlUrl,
        description = description,
        fork = fork,
        url = url,
        forksUrl = forksUrl,
        keysUrl = keysUrl,
        collaboratorsUrl = collaboratorsUrl,
        teamsUrl = teamsUrl,
        hooksUrl = hooksUrl,
        issueEventsUrl = issueEventsUrl,
        eventsUrl = eventsUrl,
        assigneesUrl = assigneesUrl,
        branchesUrl = branchesUrl,
        tagsUrl = tagsUrl,
        blobsUrl = blobsUrl,
        gitTagsUrl = gitTagsUrl,
        gitRefsUrl = gitRefsUrl,
        treesUrl = treesUrl,
        statusesUrl = statusesUrl,
        languagesUrl = languagesUrl,
        stargazersUrl = stargazersUrl,
        contributorsUrl = contributorsUrl,
        subscribersUrl = subscribersUrl,
        subscriptionUrl = subscriptionUrl,
        commitsUrl = commitsUrl,
        gitCommitsUrl = gitCommitsUrl,
        commentsUrl = commentsUrl,
        issueCommentUrl = issueCommentUrl,
        contentsUrl = contentsUrl,
        compareUrl = compareUrl,
        mergesUrl = mergesUrl,
        archiveUrl = archiveUrl,
        downloadsUrl = downloadsUrl,
        issuesUrl = issuesUrl,
        pullsUrl = pullsUrl,
        milestonesUrl = milestonesUrl,
        notificationsUrl = notificationsUrl,
        labelsUrl = labelsUrl,
        releasesUrl = releasesUrl,
        deploymentsUrl = deploymentsUrl,
        createdAt = createdAt,
        updatedAt = updatedAt,
        pushedAt = pushedAt,
        gitUrl = gitUrl,
        sshUrl = sshUrl,
        cloneUrl = cloneUrl,
        svnUrl = svnUrl,
        homepage = homepage,
        size = size,
        stargazersCount = stargazersCount,
        watchersCount = watchersCount,
        language = language,
        hasIssues = hasIssues,
        hasProjects = hasProjects,
        hasDownloads = hasDownloads,
        hasWiki = hasWiki,
        hasPages = hasPages,
        hasDiscussions = hasDiscussions,
        forksCount = forksCount,
        mirrorUrl = mirrorUrl,
        archived = archived,
        disabled = disabled,
        openIssuesCount = openIssuesCount,
        license = license?.toLicense(),
        allowForking = allowForking,
        isTemplate = isTemplate,
        webCommitSignoffRequired = webCommitSignoffRequired,
        topics = topics,
        visibility = visibility,
        forks = forks,
        openIssues = openIssues,
        watchers = watchers,
        defaultBranch = defaultBranch
    )
}

private fun PullRequestsResponse.LinksResponse.toLinks(): PullRequests.Links {
    return PullRequests.Links(
        self = self.toLink(),
        html = html.toLink(),
        issue = issue.toLink(),
        comments = comments.toLink(),
        reviewComments = reviewComments.toLink(),
        reviewComment = reviewComment.toLink(),
        commits = commits.toLink(),
        statuses = statuses.toLink()
    )
}

private fun PullRequestsResponse.LinkResponse.toLink(): PullRequests.Link {
    return PullRequests.Link(
        href = href
    )
}