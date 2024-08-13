package com.akirachix.postapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akirachix.postapp.repository.PostRepository
import com.akirachix.postapp.model.Post
import kotlinx.coroutines.launch

class PostsViewModel: ViewModel() {
    val postsRepo = PostRepository()
    val errorLiveData = MutableLiveData<String>()
    val postsLiveData = MutableLiveData<List<Post>>()
    val postLiveData = MutableLiveData<Post>()
    val commentsLiveData = MutableLiveData<List<Comments>>()


    fun fetchPosts(){
        viewModelScope.launch {
            val response = postsRepo.fetchPosts()
            if (response.isSuccessful){
                postsLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

    fun fetchPostsById(postId:Int){
        viewModelScope.launch {
            val response = postsRepo.fetchPostById(postId)
            if (response.isSuccessful){
                postLiveData.postValue(response.body())
            }
            else {
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

    fun fetchComments(postId: Int){
        viewModelScope.launch {
            val response = postsRepo.fetchComments(postId)
            if(response.isSuccessful) {
                commentsLiveData.postValue(response.body())
            } else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }



            }
