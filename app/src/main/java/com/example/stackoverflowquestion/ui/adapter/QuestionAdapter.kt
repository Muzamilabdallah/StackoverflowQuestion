package com.example.stackoverflowquestion.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stackoverflowquestion.R
import com.example.stackoverflowquestion.extention.inflate
import com.example.stackoverflowquestion.model.Item
import kotlinx.android.synthetic.main.question_item_row.view.*


class QuestionAdapter(val context: Context,val list:ArrayList<Item> ,val  clicklisnter:QuestionAdapter.implOnlicklistener):RecyclerView.Adapter<QuestionAdapter.viewHolder>() {


    class viewHolder(iteview: View) : RecyclerView.ViewHolder(iteview) {
        fun onbind(context: Context, item: Item, clicklisnter: implOnlicklistener) {

            with(itemView) {

                title.text = item.title
                name.text = item.owner.display_name
                Glide.with(context).load(item.owner.profile_image).centerCrop().into(userImage)
            }

            itemView.setOnClickListener {



                clicklisnter.Onlick(item)
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {

        val view = parent.inflate(R.layout.question_item_row, false)


        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {


        val item = list.get(position)

        holder.onbind(context, item ,clicklisnter)
    }

    override fun getItemCount(): Int {
        return list.count()
    }


    interface implOnlicklistener {

        fun Onlick(item: Item)
    }

}