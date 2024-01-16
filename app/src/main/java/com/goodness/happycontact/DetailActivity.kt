package com.goodness.happycontact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.goodness.happycontact.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
	private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)


		val receivedIntent = intent
		val receivedBundle = receivedIntent.extras

		if (receivedBundle != null) {
			val profileImage = receivedBundle.getInt("profileImage", 0)
			val name = receivedBundle.getString("name", "")
			val like = receivedBundle.getBoolean("like", false)
			val phoneNumber = receivedBundle.getString("phoneNumber", "")
		}
	}
}