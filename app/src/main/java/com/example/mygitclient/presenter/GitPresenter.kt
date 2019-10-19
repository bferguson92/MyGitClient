package com.example.mygitclient.presenter

import com.example.mygitclient.factory.GitFactory
import com.example.mygitclient.model.Owner
import com.example.mygitclient.model.RepositoryResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GitPresenter(private val view: GitContract.View) : GitContract.Presenter {

    private val gitFactory = GitFactory()

    override fun getRepos(user: String) {
        gitFactory.getRepos(user).enqueue(object : Callback<List<RepositoryResult>> {
            override fun onFailure(call: Call<List<RepositoryResult>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<List<RepositoryResult>>,
                response: Response<List<RepositoryResult>>
            ) {
                response.body()?.let{repos->
                    view.displayRepos(repos)
                }
            }
        })
    }

    override fun getUser(user: String) {
        gitFactory.getUser(user).enqueue(object : Callback<Owner>{
            override fun onFailure(call: Call<Owner>, t: Throwable) {

            }

            override fun onResponse(call: Call<Owner>, response: Response<Owner>) {
                response.body()?.let{
                    view.displayUser(it)
                }
            }

        })
    }
}