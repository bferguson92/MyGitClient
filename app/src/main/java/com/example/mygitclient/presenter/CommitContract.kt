package com.example.mygitclient.presenter

import com.example.mygitclient.model.CommitResult

interface CommitContract{
    interface Presenter {
        fun getCommits(user: String, repo: String)
    }

    interface View {
        fun displayCommits(commits: List <CommitResult>)
    }
}