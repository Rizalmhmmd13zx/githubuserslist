package com.example.githubuserslist.Adapter

import com.bumptech.glide.Glide
import com.example.githubuserslist.databinding.UserItemsBinding
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubuserslist.R
import com.example.githubuserslist.UserDetail
import com.example.githubuserslist.entity.FavoriteItems
import com.example.githubuserslist.entity.UserItems
import java.util.*


class FavoriteAdapter(private val activity: Activity) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    var listFavoriteUser = ArrayList<FavoriteItems>()
            set(listFavoriteUser) {
            if (listFavoriteUser.size > 0) {
                this.listFavoriteUser.clear()
            }
            this.listFavoriteUser.addAll(listFavoriteUser)

            notifyDataSetChanged()
        }
//    fun setData(items: ArrayList<FavoriteItems>) {
//        if (listFavoriteUser.size > 0) {
//            this.listFavoriteUser.clear()
//        }
//        listFavoriteUser.addAll(items)
//        notifyDataSetChanged()
//    }

    fun addItem(favorite: FavoriteItems) {
        this.listFavoriteUser.add(favorite)
        notifyItemInserted(this.listFavoriteUser.size - 1)
    }

    fun updateItem(position: Int, favorite: FavoriteItems) {
        this.listFavoriteUser[position] = favorite
        notifyItemChanged(position, favorite)
    }

    fun removeItem(position: Int) {
        this.listFavoriteUser.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listFavoriteUser.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_items, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavoriteUser[position])
        val data = listFavoriteUser[position]
        holder.itemView.setOnClickListener {
            val dataUserIntent = UserItems(
                data.name,
                data.username,
                data.profile_picture,
                data.followers,
                data.following,
                data.location
            )
            val mIntent = Intent(it.context, UserDetail::class.java)
            mIntent.putExtra(UserDetail.EXTRA_USERNAME, dataUserIntent)
            it.context.startActivity(mIntent)
        }
    }


    inner class FavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = UserItemsBinding.bind(itemView)
        fun bind(favoriteItems: FavoriteItems) {
            with(itemView){
            binding.tvUsername.text = favoriteItems.username
                if(favoriteItems.name == "null") binding.tvNama.text = resources.getString(R.string.null_name)
                else binding.tvNama.text = favoriteItems.name
                Glide.with(binding.imgPp)
                    .load(favoriteItems.profile_picture)
                    .into(binding.imgPp)

//                itemView.setOnClickListener(
//                        CustomOnItemClickListener(
//                                adapterPosition,
//                                object : CustomOnItemClickListener.OnItemClickCallback {
//                                    override fun onItemClicked(view: View, position: Int) {
//                                        val mIntent = Intent(activity, UserDetail::class.java)
//                                        mIntent.putExtra(UserDetail.EXTRA_USERNAME, dataUserIntent)
////                                        intent.putExtra(UserDetail.EXTRA_POSITION, position)
////                                        intent.putExtra(UserDetail.EXTRA_NOTE, fav)
//                                        activity.startActivity(intent)
//                                    }
//                                }
//                        )
//                )
            }
        }

    }

    override fun getItemCount(): Int = this.listFavoriteUser.size

}