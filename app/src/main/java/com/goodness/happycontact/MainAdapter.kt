package com.goodness.happycontact

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
	private val fragmentList = listOf(
		ContactList(),
		MyPage()
	)

	override fun getItemCount(): Int {
		return fragmentList.size
	}

	override fun createFragment(p0: Int): Fragment {
		return fragmentList[p0]
	}
}
//