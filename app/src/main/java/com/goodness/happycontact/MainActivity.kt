package com.goodness.happycontact

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.goodness.happycontact.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)




		val bundle = Bundle().apply {
			putInt("profileImage", R.drawable.break_sparta)
			putString("name", "파괴하는 르탄이")
			putBoolean("like", false)
			putString("phoneNumber", "01012341234")
		}

		val intent = Intent(this, DetailActivity::class.java)
		intent.putExtras(bundle)
		startActivity(intent)
	}
}