package com.example.stackoverflowquestion.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stackoverflowquestion.R
import com.example.stackoverflowquestion.extention.inflate
import com.example.stackoverflowquestion.model.Item
import kotlinx.android.synthetic.main.answer_item_row.view.*
import kotlinx.android.synthetic.main.question_item_row.view.*


class AnswersAdapter(val context: Context, val list:ArrayList<Item>):RecyclerView.Adapter<AnswersAdapter.viewHolder>(){




    class viewHolder(iteview: View):RecyclerView.ViewHolder(iteview){
        fun onbind(context: Context,item: Item) {

            with(itemView){
                answer.text=item.link
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        val view= parent.inflate(R.layout.answer_item_row,false)


        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {


        val item=list.get(position)

        holder.onbind(context,item)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

}