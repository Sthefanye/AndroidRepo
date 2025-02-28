package com.example.androidrepo.data.mappers

import com.example.androidrepo.data.model.ItemsResponse
import com.example.androidrepo.data.model.LicenseResponse
import com.example.androidrepo.data.model.OwnerResponse
import com.example.androidrepo.data.model.RepositoriesResponse
import com.example.androidrepo.domain.model.Items
import com.example.androidrepo.domain.model.License
import com.example.androidrepo.domain.model.Owner
import com.example.androidrepo.domain.model.Repositories

fun RepositoriesResponse.toDomain(): Repositories {
    return Repositories(
        totalCounts = totalCounts,
        incompleteResults = incompleteResults,
        items = items.map { it.toItems() })
}

private fun ItemsResponse.toItems(): Items {
    return Items(
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
        defaultBranch = defaultBranch,
        score = score
    )
}

fun OwnerResponse.toOwner(): Owner {
    return Owner(
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

fun LicenseResponse.toLicense(): License {
    return License(
        key = key,
        name = name,
        spdxId = spdxId,
        url = url,
        nodeId = nodeId
    )
}