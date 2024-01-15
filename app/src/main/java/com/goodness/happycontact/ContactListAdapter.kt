package com.goodness.happycontact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.goodness.happycontact.databinding.ContactItemBinding

class ContactListAdapter(
	private val dataList: List<Contact>,
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
			icon.setImageResource(data.profileImage)
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
		}
	}
}