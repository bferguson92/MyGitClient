package com.example.mygitclient.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mygitclient.R
import com.example.mygitclient.adapter.RepoAdapter
import com.example.mygitclient.model.Owner
import com.example.mygitclient.model.RepositoryResult
import com.example.mygitclient.presenter.GitContract
import com.example.mygitclient.presenter.GitPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GitContract.View {

    val gitPresenter = GitPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        button_enter.setOnClickListener{
            gitPresenter.getRepos(edit_text_user.text.toString())
            gitPresenter.getUser(edit_text_user.text.toString())
        }
    }

    override fun onResume() {
        super.onResume()
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//        supportActionBar?.hide()
    }

    override fun displayRepos(repoList: List<RepositoryResult>) {
        recycler_view_repos.adapter = RepoAdapter(repoList)
        recycler_view_repos.layoutManager = LinearLayoutManager(this)

    }

    override fun displayUser(user: Owner) {
        text_view_user.text = user.login
        Glide.with(this).load(user.avatarUrl).into(image_view_user)
    }

    override fun sendError() {
    }
}