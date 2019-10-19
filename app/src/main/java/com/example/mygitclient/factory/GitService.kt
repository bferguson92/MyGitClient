package com.example.mygitclient.factory

import com.example.mygitclient.model.Owner
import com.example.mygitclient.model.RepositoryResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitService {

    @GET("/users/{user_name}/repos")
    fun getMyRepos(@Path("user_name") userName: String): Call<List<RepositoryResult>>

    @GET("/users/{user_name}")
    fun getUser(@Path("user_name") userName: String): Call<Owner>
}