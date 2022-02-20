package com.example.searchimage.ui

import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchimage.R
import com.example.searchimage.data.DataList
import com.example.searchimage.data.UnsplashData


class PhotoAdapter : RecyclerView.Adapter<PhotoAdapter .MyViewHolder>() {

    var items = ArrayList<DataList>()

    fun setListData(data: ArrayList<DataList>){
        Log.i("Data", "данные: $data")
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return MyViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){

        var tvName = view.findViewById<TextView>(R.id.tvName)
        var ivPhoto = view.findViewById<ImageView>(R.id.ivPhoto)
        fun bind(data: DataList){
               if(!TextUtils.isEmpty(data.description)){
                   tvName.text = data.description
               }
                else{
                    tvName.text = "Нет записи"
               }

//                Glide.with(ivPhoto)
//                    .load(data.url.component1())
//                    .into(ivPhoto)
        }

    }

}