package com.goodness.happycontact

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goodness.happycontact.databinding.ContactItemBinding

class ContactListAdapter(
	private val context: Context,
	private var dataList: List<Contact>,
	private val onLikeButtonClick: (Int) -> Unit
) : RecyclerView.Adapter<ContactListAdapter.Holder>() {
	inner class Holder(private val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root) {
		val icon = binding.ivContactIcon
		val contactName = binding.tvContactName
		val like = binding.ivLike
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
		val binding = ContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return Holder(binding)
	}

	override fun getItemCount(): Int {
		return dataList.size
	}

	override fun onBindViewHolder(holder: Holder, position: Int) {
		val data = dataList[position]

		holder.apply {

			if (data.profileImageUri.isNullOrBlank() ){
				icon.setImageResource(data.profileImage)
			}else{
				icon.setImageURI(Uri.parse(data.profileImageUri))
			}

			contactName.text = data.name

			if (data.like) {
				like.setImageResource(R.drawable.heart_filled)
			} else {
				like.setImageResource(R.drawable.heart)
			}

			like.setOnClickListener {
				onLikeButtonClick(position)
				notifyItemChanged(position)
			}

			itemView.setOnClickListener {
				val bundle = Bundle()
				bundle.putParcelable(Contact.CONTACT_KEY, data)

				val intent = Intent(context, DetailActivity::class.java)
				intent.putExtras(bundle)
				context.startActivity(intent)
			}
		}

		}
	fun updateData(newData: List<Contact>) {
		dataList = newData  // dataList을 새로운 데이터로 갱신
		notifyDataSetChanged()
	}
}