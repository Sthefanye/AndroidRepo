package com.example.androidrepo.di

import com.example.androidrepo.data.repository.GithubRepositoriesImpl
import com.example.androidrepo.domain.repository.IGithubRepositories
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsGithubRepositoriesImpl(
        githubRepositoriesImpl: GithubRepositoriesImpl
    ): IGithubRepositories
}
