package com.example.stackoverflowquestion.repository

import com.example.stackoverflowquestion.data.RetrofitService

object   QuestionRepo {
    suspend fun getAllQuestions()= RetrofitService.api.getAllQuestions(50,"stackoverflow")


    suspend fun getQuestionAnswers(id:Int)= RetrofitService.api.getQuestionAnswers(id ,5,"stackoverflow")
}


