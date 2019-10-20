package com.example.mygitclient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.mygitclient.R
import com.example.mygitclient.model.CommitResult

class CommitAdapter(private val commitList: List<CommitResult>) : RecyclerView.Adapter<CommitAdapter.CustomViewHolder>(){
    private lateinit var glide: RequestManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        glide = Glide.with(parent.context)
        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.commit_item, parent, false))
    }


    override fun getItemCount(): Int {
        return commitList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.apply {
            committerName.text = commitList[position].committer.login
            commitDate.text = commitList[position].commit.committer.date
            glide.load(commitList[position].committer.avatarUrl).into(committerAvatar)
            commitMessage.text = commitList[position].commit.message
        }
    }


    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val committerName: TextView = view.findViewById(R.id.text_view_committer_name)
        val commitDate: TextView = view.findViewById(R.id.text_view_date)
        val committerAvatar: ImageView = view.findViewById(R.id.image_view_user_avatar)
        val commitMessage: TextView = view.findViewById(R.id.text_view_message)
    }
}