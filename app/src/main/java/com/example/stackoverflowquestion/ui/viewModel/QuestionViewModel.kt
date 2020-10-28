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

class QuestionViewModel :ViewModel(){




      val questions=MutableLiveData<List<Item>>()



    val error=MutableLiveData<String>()






    fun  getAllquestion(){


        viewModelScope.launch {


            val response = QuestionRepo.getAllQuestions()


            if (response.isSuccessful){

                questions.postValue(response.body()?.items)

            }
            else{

                error.postValue(response.message())

            }
        }

    }





    }

