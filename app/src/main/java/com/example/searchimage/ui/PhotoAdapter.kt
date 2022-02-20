package com.example.searchimage.ui

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchimage.R
import com.example.searchimage.data.DataList


class PhotoAdapter (context: Context) : RecyclerView.Adapter<PhotoAdapter .MyViewHolder>() {

    var items = ArrayList<DataList>()
    val _context = context

    fun setListData(data: ArrayList<DataList>){
        Log.i("Data", "данные: $data")
        this.items = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return MyViewHolder(_context, inflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(_context, items[position])
    }

    override fun getItemCount(): Int = items.size

    class MyViewHolder(context: Context, view: View): RecyclerView.ViewHolder(view){

        var tvName = view.findViewById<TextView>(R.id.tvName)
        var ivPhoto = view.findViewById<ImageView>(R.id.ivFullPhoto)
        fun bind(context: Context,data: DataList){
               if(!TextUtils.isEmpty(data.description)){
                   tvName.text = data.description
               }
                else{
                    tvName.text = "Untitled"
               }

                Glide.with(ivPhoto)
                    .load(data.url.component1())
                    .into(ivPhoto)

                itemView.setOnClickListener() {

                    val intent = Intent(context, FullPhotoActivity::class.java).apply {
                        putExtra("Photo", data.url.component2())
                        putExtra("Title", data.description)
                    }
                    context.startActivity(intent)
                }

        }


    }

}