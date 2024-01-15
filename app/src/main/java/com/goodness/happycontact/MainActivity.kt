package com.goodness.happycontact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.goodness.happycontact.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		setSwiper()
	}

	private fun setSwiper() {
		val viewPager = binding.pager

		viewPager.adapter = MainAdapter(this)
	}
}