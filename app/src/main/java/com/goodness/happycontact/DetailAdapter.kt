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
        return contactList.size * 7
    }
    //표시되어야 할 속성 개수를 반환

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = contactList[position / 7]
        //해당 포지션에서 7개의 인텍스를 공간을 contact에 생성하고
        when (position % 7) {
            //각 인덱스에 할당될 데이터를 지정
            0 -> {
                holder.title.text = "전화번호"
                holder.des.text = contact.phoneNumber
            }
            1 -> {
                holder.title.text = "이메일"
                holder.des.text = contact.email
            }
            2 -> {
                holder.title.text = "주소"
                holder.des.text = contact.address
            }
            3 -> {
                holder.title.text = "그룹"
                holder.des.text = contact.relationship
            }
            4 -> {
                holder.title.text = "이벤트"
                holder.des.text = contact.bigDay
            }
            5 -> {
                holder.title.text = "생일"
                holder.des.text = contact.birthDay
            }
            6 -> {
                holder.title.text = "메모"
                holder.des.text = contact.memo
            }
        }
    }
    //

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_card_title)
        val des: TextView = itemView.findViewById(R.id.tv_card_des)
    }
    //상세 정보를 표시하기 위해 두 개의 텍스트 뷰를 캡슐화
}