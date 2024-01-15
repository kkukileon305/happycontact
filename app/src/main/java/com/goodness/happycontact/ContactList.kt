package com.goodness.happycontact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodness.happycontact.databinding.FragmentContactListBinding

class ContactList : Fragment() {
	private lateinit var binding: FragmentContactListBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentContactListBinding.inflate(inflater, container, false)

		val recyclerView = binding.rvContactList

		val contactListAdapter = ContactListAdapter(Contact.DATA) { position ->
			val clickedData = Contact.DATA[position]
			clickedData.like = !clickedData.like
		}

		recyclerView.adapter = contactListAdapter
		recyclerView.layoutManager = LinearLayoutManager(requireContext())

		return binding.root
	}
}