package com.example.morningnetwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recycler_view)
        getNews()
    }
    private fun getNews(){
        val news=NewsService.newsInstance.fetchHeadlines("in",Constants.API_KEY)
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val newsResponse=response.body()
                newsResponse?.let {
                    recyclerView.adapter=NewsAdapter(newsResponse.articles,this@MainActivity)
                }
                recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.e("MainActivity",t.message.toString())
            }

        })
    }
}