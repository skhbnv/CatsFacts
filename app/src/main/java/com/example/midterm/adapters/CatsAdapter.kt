package com.example.midterm.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm.R
import com.example.midterm.data.response.DefaultResponse
import kotlinx.android.synthetic.main.fact_card.view.*

class CatsAdapter (
    var itemsList: List<DefaultResponse>
) : RecyclerView.Adapter<CatsAdapter.DefaultViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder {
        return DefaultViewHolder(
            LayoutInflater.from(parent.context).
                inflate(R.layout.fact_card, parent, false)
        )
    }

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: DefaultViewHolder, position: Int) {
        holder.bind(itemsList[position])
    }

    inner class DefaultViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView){

        fun bind(item: DefaultResponse){
            with(itemView){
                factsText.text = item.text
                factsType.text = item.type
                factsUploads.text = item.upvotes.toString()
            }
        }
    }
}