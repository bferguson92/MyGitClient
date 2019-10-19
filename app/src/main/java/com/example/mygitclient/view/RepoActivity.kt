package com.example.mygitclient.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mygitclient.R
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
        val stringBuilder = StringBuilder()

        commits.forEach{commit->
            stringBuilder.append("${commit.author.login}\n")
        }

        text_view_commits.text= stringBuilder
    }
}
