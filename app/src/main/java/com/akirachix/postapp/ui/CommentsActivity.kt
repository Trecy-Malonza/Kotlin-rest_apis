package com.akirachix.postapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.akirachix.postapp.databinding.ActivityCommentsBinding
import com.akirachix.postapp.viewmodel.PostsViewModel

class CommentsActivity : AppCompatActivity() {

    var postId: Int = 0
    lateinit var binding: ActivityCommentsBinding



    val postsViewModel: PostsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCommentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (intent.extras !=null){
            postId = intent.getIntExtra("POST_ID",0)


        }




    }


        override fun onResume() {
        super.onResume()
        postsViewModel.postsLiveData.observe(this){post->
            binding.tvPostTitle.text = post.title
            binding.tvPostBody.text = post.body
        }
            postsViewModel.errorLiveData.observe(this){error->
                Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
            }
    }

//    private fun setupRecyclerView() {
//        binding.rvComments.layoutManager = LinearLayoutManager(this)
//        binding.rvComments.adapter = commentsAdapter
//    }
//
//    override fun onStart() {
//        super.onStart()
//        Log.d(TAG,"COMMENTSACTIVITY ONSTART")
//    }
//

//
//    override fun onPause() {
//        super.onPause()
//        Log.d(TAG,"COMMENTSACTIVITY ONPAUSE")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.d(TAG,"COMMENTSACTIVITY ONSTOP")
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//        Log.d(TAG,"COMMENTSACTIVITY ONRESTART")
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//        Log.d(TAG,"COMMENTSACTIVITY ONDESTROY")
//    }


}
