package com.goodness.happycontact

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.goodness.happycontact.databinding.FragmentContactListBinding
import com.goodness.happycontact.databinding.FragmentMyPageBinding

class MyPage : Fragment() {
	private lateinit var binding: FragmentMyPageBinding
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentMyPageBinding.inflate(inflater, container, false)
		return binding.root
	}
}