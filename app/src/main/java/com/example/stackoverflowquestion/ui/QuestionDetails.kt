package com.example.stackoverflowquestion.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.stackoverflowquestion.R
import com.example.stackoverflowquestion.extention.toast
import com.example.stackoverflowquestion.model.Item
import com.example.stackoverflowquestion.repository.QuestionRepo
import com.example.stackoverflowquestion.ui.adapter.AnswersAdapter
import kotlinx.android.synthetic.main.activity_question_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuestionDetails : AppCompatActivity() {

    lateinit var adapter: AnswersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_details)

        val intent = this.getIntent()


        val id = intent.getIntExtra("id",0)
        val Name = intent.getStringExtra("name")
        val Title = intent.getStringExtra("title")
        val image = intent.getStringExtra("image")

        name.text = Name
        txttitle.text=Title

        Glide.with(this).load(image).centerCrop().into(userImage)
        initiateRecyclerview()


        Log.d("id",id.toString())
        fecthData(id)
    }




    private fun initiateRecyclerview() {


        val layoutManager = LinearLayoutManager(this)
        answerrecyclerView.layoutManager = layoutManager

    }
    private fun fecthData(id: Int) {

        answerprogress.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {

                val response = QuestionRepo.getQuestionAnswers(id)

                if (response.isSuccessful) {

                    answerprogress.visibility = View.INVISIBLE
                    if (response.body()?.items.isNullOrEmpty()){


                        message.text="No answers Found"
                    }
                    else {

                        adapter = AnswersAdapter(
                            this@QuestionDetails,
                            response.body()?.items as ArrayList<Item>
                        )

                        answerrecyclerView.adapter = adapter
                        adapter.notifyDataSetChanged()

                    }

                } else {
                    answerprogress.visibility = View.INVISIBLE
                    toast("something b goes wrong")

                }

            }
        }

    }
}