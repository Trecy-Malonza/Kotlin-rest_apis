package com.akirachix.postapp

import android.net.DnsResolver.Callback
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akirachix.postapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
        lateinit var binding: ActivityMainBinding
        lateinit var recyclerView: RecyclerView

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)
            fetchPosts()
            recyclerView = binding.tvPosts
            recyclerView.layoutManager = LinearLayoutManager(this)
        }
        fun fetchPosts() {
            val
            val apiClient = ApiClient.buildApiClient(PostsApiInterface::class.java)
            val request = apiClient.fetchPosta()
            request.enqueue(object :retrofit2.Callback<List<Post>>{
                override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                    if (response.isSuccessful) {
                        val posts = response.body()
                        posts?.let {
                            postAdapter = PostAppAdapter(it)
                            recyclerView.adapter = postAdapter
                        }
                        Toast.makeText(baseContext, "Fetched ${posts!!.size} posts", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
                    }
                }
                override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                    Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }