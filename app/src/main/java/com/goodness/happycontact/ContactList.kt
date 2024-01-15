package com.goodness.happycontact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.goodness.happycontact.databinding.FragmentContactListBinding

class ContactList : Fragment() {
	lateinit var binding: FragmentContactListBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentContactListBinding.inflate(inflater, container, false)
		return binding.root
	}
}