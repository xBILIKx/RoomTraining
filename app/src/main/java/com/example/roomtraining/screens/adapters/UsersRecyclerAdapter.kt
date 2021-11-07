package com.example.roomtraining.screens.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomtraining.R
import com.example.roomtraining.databinding.UserCardBinding
import com.example.roomtraining.model.User

class UsersRecyclerAdapter : RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder>() {

    private var usersList = emptyList<User>()

    fun setData(user:List<User>){
        this.usersList = user
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val binding = UserCardBinding.bind(itemView)
        fun bind(user: User)= with(binding){
            idTextView.text = user.id.toString()
            nameTextView.text = user.name
            ageTextView.text = user.age.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_card,
            parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(usersList[position])
    }

    override fun getItemCount() = usersList.size
}