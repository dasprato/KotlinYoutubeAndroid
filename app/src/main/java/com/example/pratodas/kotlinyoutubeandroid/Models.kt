package com.example.pratodas.kotlinyoutubeandroid

/**
 * Created by pratodas on 2017-12-23.
 */


class HomeFeed(val videos: List<Video>)

class Video(val id: Int, val name: String, val link: String, val imageUrl: String, numberOfViews: Int, val channel: Channel)

class Channel(val name: String, val profileImageUrl: String)


class CourseLesson(val name: String, val duration: String, val number: String, val imageUrl: String, val link: String)