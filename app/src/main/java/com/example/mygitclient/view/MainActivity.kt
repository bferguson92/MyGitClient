package com.example.mygitclient.view

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mygitclient.R
import com.example.mygitclient.adapter.RepoAdapter
import com.example.mygitclient.model.Owner
import com.example.mygitclient.model.RepositoryResult
import com.example.mygitclient.presenter.GitContract
import com.example.mygitclient.presenter.GitPresenter
import com.example.mygitclient.receiver.AlarmReciever
import com.example.mygitclient.util.Constants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GitContract.View, RepoAdapter.RepoAdaterDelegate {

    private val gitPresenter = GitPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            Constants.ALARM_REQUEST_CODE,
            Intent(this, AlarmReciever::class.java),
            0
        )

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + (600000),
            pendingIntent
            )




        button_enter.setOnClickListener {
            gitPresenter.getRepos(edit_text_user.text.toString())
            gitPresenter.getUser(edit_text_user.text.toString())
        }
    }


    override fun displayRepos(repoList: List<RepositoryResult>) {
        recycler_view_repos.adapter = RepoAdapter(repoList, this)
        recycler_view_repos.layoutManager = LinearLayoutManager(this)
    }

    override fun displayUser(user: Owner) {
        text_view_user.text = user.login
        Glide.with(this).load(user.avatarUrl).into(image_view_user)
    }

    override fun sendError() {
    }

    override fun goToRepo(user: String, repo: String) {
        val intent = Intent(this, RepoActivity::class.java)

        intent.putExtra("user", user)
        intent.putExtra("repo", repo)

        startActivity(intent)
    }
}
