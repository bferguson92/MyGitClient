package com.example.mygitclient.presenter

import com.example.mygitclient.model.Owner
import com.example.mygitclient.model.RepositoryResult

interface GitContract{
    interface Presenter{
        fun getRepos(user: String)
        fun getUser(user: String)
    }

    interface View{
        fun displayRepos(repoList: List<RepositoryResult>)
        fun displayUser(user: Owner)
        fun sendError()
    }
}