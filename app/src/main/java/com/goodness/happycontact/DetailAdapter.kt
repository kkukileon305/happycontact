package com.goodness.happycontact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DetailAdapter(private val contactList: List<Contact>) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detail_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return contactList.size * 5
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position / 5]
        when (position % 5) {
            0 -> {
                holder.title.text = "주소"
                holder.des.text = contact.address
            }
            1 -> {
                holder.title.text = "그룹"
                holder.des.text = contact.relationship
            }
            2 -> {
                holder.title.text = "이벤트"
                holder.des.text = contact.bigDay
            }
            3 -> {
                holder.title.text = "생일"
                holder.des.text = contact.birthDay
            }
            4 -> {
                holder.title.text = "메모"
                holder.des.text = contact.memo
            }
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_card_title)
        val des: TextView = itemView.findViewById(R.id.tv_card_des)
    }
}