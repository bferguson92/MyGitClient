package com.example.mygitclient.presenter

import com.example.mygitclient.factory.GitFactory
import com.example.mygitclient.model.CommitResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommitPresenter(private val view: CommitContract.View) : CommitContract.Presenter {

    private val gitFactory = GitFactory()

    override fun getCommits(user: String, repo: String) {
        gitFactory.getRepoCommits(user, repo).enqueue(object : Callback<List<CommitResult>>{
            override fun onFailure(call: Call<List<CommitResult>>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<List<CommitResult>>,
                response: Response<List<CommitResult>>
            ) {
                response.body()?.let{
                    view.displayCommits(it)
                }
            }
        })
    }

}