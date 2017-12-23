package com.example.pratodas.kotlinyoutubeandroid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.video_row.view.*

/**
 * Created by pratodas on 2017-12-22.
 */

class MainAdapter(val homeFeed: HomeFeed): RecyclerView.Adapter<CustomViewHolder>() {

    val videoTitles = listOf("First title", "Second", "3rd", "MOOOOOORE TITLES")
    // numberOfItemsInSection in iOS
    override fun getItemCount(): Int {
        return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomViewHolder {
        val layoutInflator = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflator.inflate(R.layout.video_row, parent, false)
        return CustomViewHolder(cellForRow)
    }

    // modifying the cell
    override fun onBindViewHolder(holder: CustomViewHolder?, position: Int) {
//        val videoTitle = videoTitles.get(position)
        val video = homeFeed.videos.get(position)

        holder?.view?.textView_video_title?.text = video.name
        holder?.view?.textView_channel_name?.text = video.channel.name + "  •  " + "20K Views\n4 days ago"

        val thumbnailImageView = holder?.view?.imageView_video_thumbnail
        Picasso.with(holder?.view?.context).load(video.imageUrl).into(thumbnailImageView)

        val channelProfileImageView = holder?.view?.imageView_channel_profile
        Picasso.with(holder?.view?.context).load(video.channel.profileImageUrl).into(channelProfileImageView)
    }

}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view) {

}