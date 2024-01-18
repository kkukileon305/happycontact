package com.goodness.happycontact

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.goodness.happycontact.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
	private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		val receivedIntent = intent
		val receivedBundle = receivedIntent.extras

		// Bundle에서 연락처 정보 추출
		val contact = receivedBundle?.getParcelable<Contact>(Contact.CONTACT_KEY)

		// 연락처 정보가 null이 아닌 경우에만 표시
		if (contact != null) {
			// 연락처 정보를 화면에 표시하는 작업 수행
			val profileImageView = binding.imageView
			if (contact.profileImageUri.isNullOrBlank()){
				profileImageView.setImageResource(contact.profileImage)
			}else{
				profileImageView.setImageURI(Uri.parse(contact.profileImageUri))
			}
			binding.textView.text = contact.name
			binding.tvNumber.text = contact.phoneNumber
			binding.tvNumber2.text = contact.email
		}
	}
}