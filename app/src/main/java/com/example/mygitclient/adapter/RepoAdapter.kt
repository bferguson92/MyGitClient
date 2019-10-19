package com.example.mygitclient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mygitclient.R
import com.example.mygitclient.model.RepositoryResult

class RepoAdapter (private val repoList: List<RepositoryResult>, private val repoAdapterDelegate: RepoAdaterDelegate) : RecyclerView.Adapter<RepoAdapter.CustomViewHolder>(){
    interface RepoAdaterDelegate{
        fun goToRepo(user: String, repo: String)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        return CustomViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.repo_item, parent, false))
    }

    override fun getItemCount(): Int {
        return repoList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.apply {
            repoName.text = repoList[position].name
            repoName.setOnClickListener{
                repoAdapterDelegate.goToRepo(repoList[position].owner.login, repoList[position].name)
            }
        }
    }

    class CustomViewHolder(view: View): RecyclerView.ViewHolder(view){
        val repoName: TextView = view.findViewById(R.id.text_view_repo_name)
    }

}