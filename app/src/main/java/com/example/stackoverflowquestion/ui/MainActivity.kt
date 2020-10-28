package com.example.stackoverflowquestion.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stackoverflowquestion.R
import com.example.stackoverflowquestion.extention.toast
import com.example.stackoverflowquestion.model.Item
import com.example.stackoverflowquestion.repository.QuestionRepo
import com.example.stackoverflowquestion.ui.adapter.QuestionAdapter
import com.example.stackoverflowquestion.ui.viewModel.QuestionViewModel
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var adapter:QuestionAdapter
    lateinit var questionViewModel: QuestionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


questionViewModel=ViewModelProvider(this).get(QuestionViewModel::class.java)

        initiateRecyclerview()


        fecthData()
    }


    private fun initiateRecyclerview() {


        val layoutManager = LinearLayoutManager(this)
        questionList.layoutManager = layoutManager

    }


    private fun fecthData() {

        progressBar.visibility=View.VISIBLE


        questionViewModel.getAllquestion()

        questionViewModel.questions.observe(this, Observer {

            progressBar.visibility=View.INVISIBLE

            adapter= QuestionAdapter(this@MainActivity,it as  ArrayList<Item>,object:QuestionAdapter.implOnlicklistener{

                override fun Onlick(item: Item) {


                    val intent=Intent(this@MainActivity,QuestionDetails::class.java)

                    intent.putExtra("name",item.owner.display_name)

                    intent.putExtra("title",item.title)

                    intent.putExtra("image",item.owner.profile_image)
                    intent.putExtra("id",item.question_id)
                    startActivity(intent)

                }
            })

            questionList.adapter=adapter
            adapter.notifyDataSetChanged()


        })



        questionViewModel.error.observe(this, Observer {
            progressBar.visibility=View.INVISIBLE

        })



    }
}