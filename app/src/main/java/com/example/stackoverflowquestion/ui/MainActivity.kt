package com.example.stackoverflowquestion.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stackoverflowquestion.R
import com.example.stackoverflowquestion.extention.toast
import com.example.stackoverflowquestion.model.Item
import com.example.stackoverflowquestion.repository.QuestionRepo
import com.example.stackoverflowquestion.ui.adapter.QuestionAdapter
import kotlinx.android.synthetic.main.activity_main3.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    lateinit var adapter:QuestionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)




        initiateRecyclerview()


        fecthData()
    }


    private fun initiateRecyclerview() {


        val layoutManager = LinearLayoutManager(this)
        questionList.layoutManager = layoutManager

    }


    private fun fecthData() {

        progressBar.visibility=View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {

                val response = QuestionRepo.getAllQuestions()

                if (response.isSuccessful) {
                    progressBar.visibility=View.INVISIBLE
                    adapter= QuestionAdapter(this@MainActivity,response.body()?.items as  ArrayList<Item>,object:QuestionAdapter.implOnlicklistener{

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

                } else {
                    progressBar.visibility=View.INVISIBLE
                    toast("something b goes wrong")

                }

            }
        }

    }
}