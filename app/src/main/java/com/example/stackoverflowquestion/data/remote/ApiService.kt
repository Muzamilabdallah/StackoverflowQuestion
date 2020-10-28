package com.example.stackoverflowquestion.data.remote

import com.example.stackoverflowquestion.model.Question
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @GET("questions")
    suspend fun getAllQuestions(@Query("pagesize") size:Int,@Query("site") siteName:String): Response<Question>


    @GET("answers/{id}")
    suspend fun getQuestionAnswers(@Path("id") id:Int,@Query("pagesize") size:Int, @Query("site") siteName:String): Response<Question>


}