package com.goodness.happycontact

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.goodness.happycontact.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val receivedIntent = intent
        val receivedBundle = receivedIntent.extras
        val ivBack: ImageView = findViewById(R.id.ivBack)

//         Bundle에서 연락처 정보 추출
        contact = receivedBundle?.getParcelable<Contact>(Contact.CONTACT_KEY)
//        val contact = receivedBundle?.getParcelable<Contact>(Contact.CONTACT_KEY)


        // 연락처 정보가 null이 아닌 경우에만 표시
        contact?.let {
            val profileImageView = binding.imageView
            if (it.profileImageUri.isNullOrBlank()){
                profileImageView.setImageResource(it.profileImage)
            }else{
                profileImageView.setImageURI(Uri.parse(it.profileImageUri))
            }
            binding.textView.text = it.name
            binding.tvMobile2.text = it.phoneNumber
            binding.tvEmail2.text = it.email
        }

            updateLikeButtonImage()

            // 좋아요 상태 토글
            binding.ivLike.setOnClickListener {
                contact?.like = !(contact?.like ?: false)
                // 좋아요 상태에 따라 이미지 설정
                updateLikeButtonImage()
            }
        // ImageView에 클릭 리스너 추가
        ivBack.setOnClickListener {
            // 클릭 시 뒤로 가는 동작 수행
            onBackPressed()
        }
    }


    // 좋아요 버튼 이미지 업데이트 함수
    private fun updateLikeButtonImage() {
        if (contact?.like == true) {
            binding.ivLike.setImageResource(R.drawable.heart_filled)
        } else {
            binding.ivLike.setImageResource(R.drawable.heart)
        }
    }
}
