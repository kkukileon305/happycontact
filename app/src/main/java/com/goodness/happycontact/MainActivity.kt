package com.goodness.happycontact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.goodness.happycontact.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		setSwiper()
	}

	private fun setSwiper() {
		val viewPager = binding.pager
		val tabLayout = binding.tabLayout

		viewPager.adapter = MainAdapter(this)

		TabLayoutMediator(tabLayout, viewPager) { tab, position ->
			tab.text = when (position) {
				0 -> getString(R.string.main_tab_name_1)
				1 -> getString(R.string.main_tab_name_2)
				else -> ""
			}
		}.attach()
	}
}