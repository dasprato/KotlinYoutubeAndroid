package com.example.pratodas.kotlinyoutubeandroid

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

/**
 * Created by pratodas on 2017-12-26.
 */

class CourseDetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        recyclerView_main.layoutManager = LinearLayoutManager(this)
        recyclerView_main.adapter = CourseDetailAdapter()

        // we'll change the nav bar title

        val navbarTitle = intent.getStringExtra(CustomViewHolder.VIDEO_TITLE_KEY)
        supportActionBar?.title = navbarTitle
        // navBar is actionBar in the android world
        fetchJSON()
    }


    private fun fetchJSON() {

        val videoId = intent.getIntExtra(CustomViewHolder.VIDEO_ID_KEY,-1)
        val courseDetailUrl = "https://api.letsbuildthatapp.com/youtube/course_detail?id" + videoId

        val client = OkHttpClient()
        val request = Request.Builder().url(courseDetailUrl).build()
        client.newCall(request).enqueue(object: Callback{
            override fun onResponse(call: Call?, response: Response?) {
                val body = response?.body()?.string()

                val gson = GsonBuilder().create()
                gson.fromJson(body, Array<CourseLesson>::class.java)
//                println(body)
            }
            override fun onFailure(call: Call?, e: IOException?) {
            }
        })
    }
    private class CourseDetailAdapter: RecyclerView.Adapter<CourseLessonViewHolder>() {

        override fun getItemCount(): Int {
            return 5
        }
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CourseLessonViewHolder {

            val layoutInflator = LayoutInflater.from(parent?.context)
            val customView = layoutInflator.inflate(R.layout.course_lesson_row, parent, false)

//            val blueView = View(parent?.context)
//            blueView.setBackgroundColor(Color.BLUE)
//            blueView.minimumHeight = 50
            return  CourseLessonViewHolder(customView)
        }
        override fun onBindViewHolder(holder: CourseLessonViewHolder?, position: Int) {
        }
    }

    private  class CourseLessonViewHolder(val customView: View): RecyclerView.ViewHolder(customView) {

    }
}