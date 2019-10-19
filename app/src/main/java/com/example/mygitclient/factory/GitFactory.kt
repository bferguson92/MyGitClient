package com.example.mygitclient.factory

import com.example.mygitclient.model.CommitResult
import com.example.mygitclient.model.Owner
import com.example.mygitclient.model.RepositoryResult
import com.example.mygitclient.util.Constants
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitFactory {

    private var gitService: GitService

    init {
        gitService = createGitService(getRetroFitInstance())
    }

    private fun getRetroFitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun createGitService(retrofit: Retrofit): GitService{
        return retrofit.create(GitService::class.java)
    }

    fun getRepos(user: String): Call<List<RepositoryResult>>{
        return gitService.getMyRepos(user)
    }

    fun getUser(user: String): Call<Owner>{
        return gitService.getUser(user)
    }

    fun getRepoCommits(user: String, repo: String): Call<List<CommitResult>>{
        return gitService.getRepoCommits(user, repo)
    }
}