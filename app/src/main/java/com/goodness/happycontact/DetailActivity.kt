package com.goodness.happycontact

import android.app.Activity
import android.content.Intent
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

        // Bundle에서 연락처 정보 추출
        contact = receivedBundle?.getParcelable<Contact>(Contact.CONTACT_KEY)


        // 연락처 정보가 null이 아닌 경우에만 표시
        if (contact != null) {
            // 연락처 정보를 화면에 표시하는 작업 수행

            binding.textView.text = "${contact?.name}"
            binding.tvMobile2.text = "${contact?.phoneNumber}"
            binding.tvEmail2.text = "${contact?.email}"


            val profileImageView = binding.imageView
            profileImageView.setImageResource(contact?.profileImage ?: 0)
            updateLikeButtonImage()

            // 좋아요 상태 토글

//            binding.ivLike.setOnClickListener {
//                contact?.like = !(contact?.like ?: false)
//                // 좋아요 상태에 따라 이미지 설정
//                updateLikeButtonImage()
//            }
//        }

            binding.ivLike.setOnClickListener {
                contact?.let {

                        it.like = !(it.like ?: false)

                        // 업데이트된 Contact 정보를 ContactList로 전달
                        val returnIntent = Intent()
                        returnIntent.putExtra(Contact.CONTACT_KEY, it)
                        setResult(Activity.RESULT_OK, returnIntent)

                        // 좋아요 상태에 따라 이미지 설정
                        updateLikeButtonImage()
                    }
                }


                // ImageView에 클릭 리스너 추가
                ivBack.setOnClickListener {
                    // 클릭 시 뒤로 가는 동작 수행
                    onBackPressed()
                }
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






