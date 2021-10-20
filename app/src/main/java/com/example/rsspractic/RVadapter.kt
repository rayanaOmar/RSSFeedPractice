package com.example.rsspractic

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rsspractic.databinding.QuestionRowBinding

class RVadapter (val result: MutableList<Question>?) : RecyclerView.Adapter<RVadapter.ViewHolder>() {
    class ViewHolder (val binding: QuestionRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            QuestionRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val question = result!![position]

        holder.binding.apply {
            tvTitle.text = "َQuestion: ${question.title}"
            tvName.text = "By: ${question.name}"
            cardView.setOnClickListener {
                holder.itemView.context.startActivity(Intent(Intent.ACTION_VIEW).setData(Uri.parse(question.questionLink)))
            }
        }
    }

    override fun getItemCount(): Int = result!!.size

}