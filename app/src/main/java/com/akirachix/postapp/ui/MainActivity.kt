package com.akirachix.postapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.akirachix.postapp.model.Post
import com.akirachix.postapp.databinding.ActivityMainBinding
import com.akirachix.postapp.viewmodel.PostsViewModel


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val postsViewModel: PostsViewModel by viewModels()
//    var TAG ="TAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        Log.d(TAG,"MAINACTIVITY ONCREATE")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        postsViewModel.fetchPosts()

        binding.tvPosts.layoutManager = LinearLayoutManager(this)
    }
    override fun onResume() {
        super.onResume()
        postsViewModel.postsLiveData.observe(this){posts ->
            displayPosts(posts)

        }
        postsViewModel.errorLiveData.observe(this){error ->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()

        }

    }






//    override fun onStart() {
//        super.onStart()
//        Log.d(TAG,"MAINACTIVITY ONSTART")
//    }
//

//
//    override fun onPause() {
//        super.onPause()
//        Log.d(TAG,"MAINACTIVITY ONPAUSE")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d(TAG,"MAINACTIVITY ONSTOP")
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//        Log.d(TAG,"MAINACTIVITY ONRESTART")
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG,"MAINACTIVITY ONDESTROY")
//    }

    fun displayPosts(posts: List<Post>){
        val postsAdapter = PostsAdapter(posts, this)
        binding.tvPosts.adapter = postsAdapter
    }

}