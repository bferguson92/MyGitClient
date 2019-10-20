package com.example.mygitclient.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygitclient.R
import com.example.mygitclient.adapter.CommitAdapter
import com.example.mygitclient.factory.GitFactory
import com.example.mygitclient.model.CommitResult
import com.example.mygitclient.presenter.CommitContract
import com.example.mygitclient.presenter.CommitPresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_repo.*

class RepoActivity : AppCompatActivity(), CommitContract.View {

    private val commitPresenter = CommitPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        val name = intent.getStringExtra("user")
        val repo = intent.getStringExtra("repo")

        if(name != null && repo != null)
            commitPresenter.getCommits(name, repo)

    }

    override fun displayCommits(commits: List<CommitResult>) {
        recycler_view_commits.adapter = CommitAdapter(commits)
        recycler_view_commits.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }
}
