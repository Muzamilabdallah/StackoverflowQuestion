package com.example.stackoverflowquestion.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.example.stackoverflowquestion.model.Item
import com.example.stackoverflowquestion.model.Question
import com.example.stackoverflowquestion.repository.QuestionRepo
import kotlinx.coroutines.launch

class AnswerViewModel :ViewModel(){




      val answers=MutableLiveData<List<Item>>()



    val error=MutableLiveData<String>()






    fun  getAnswers(id:Int){


        viewModelScope.launch {


            val response = QuestionRepo.getQuestionAnswers(id)


            if (response.isSuccessful){

                answers.postValue(response.body()?.items)

            }
            else{

                error.postValue(response.message())

            }
        }

    }





    }

