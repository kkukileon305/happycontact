package com.goodness.happycontact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.goodness.happycontact.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
	private val adapter by lazy { MainAdapter(this) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		setSwiper()
		addButtonPressed()
	}

	private fun addButtonPressed() {
		binding.btnTest.setOnClickListener {
			AddFrag {
				val listFragment = supportFragmentManager.findFragmentByTag("f0") as ContactList

				listFragment.notifyContactInsertedData()
			}.show(supportFragmentManager, "AddFrag")
		}
	}

	private fun setSwiper() {
		val viewPager = binding.pager
		val tabLayout = binding.tabLayout

		viewPager.adapter = adapter

		TabLayoutMediator(tabLayout, viewPager) { tab, position ->
			tab.text = when (position) {
				0 -> getString(R.string.main_tab_name_1)
				1 -> getString(R.string.main_tab_name_2)
				else -> ""
			}
		}.attach()
	}
}
