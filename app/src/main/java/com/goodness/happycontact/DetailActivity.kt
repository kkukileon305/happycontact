package com.goodness.happycontact

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goodness.happycontact.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate(layoutInflater) }
    private var contact: Contact? = null
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val updatedContact = result.data?.getParcelableExtra<Contact>(Contact.CONTACT_KEY)
                updatedContact?.let {
                    updateData(it)
                }
            }
        }

    //추가
    private lateinit var recyclerView: RecyclerView
    private lateinit var contactAdapter: DetailAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        contact = intent.getParcelableExtra<Contact>(Contact.CONTACT_KEY)
        val ivBack: ImageView = findViewById(R.id.ivBack)

        updateLikeButtonImage()

        // 좋아요 상태 토글
        binding.ivLike.setOnClickListener {
            contact?.let {
                contact?.like = !(contact?.like ?: false)

                val returnIntent = Intent()
                returnIntent.putExtra(Contact.CONTACT_KEY, it)
                Log.d("DetailActivity", "ivLike Clicked")

//                resultLauncher.launch(Intent(this, MainActivity::class.java))
                // 좋아요 상태에 따라 이미지 설정
                updateLikeButtonImage()
            }
        }
        // ImageView에 클릭 리스너 추가
        ivBack.setOnClickListener {
            // 클릭 시 뒤로 가는 동작 수행
            onBackPressed()
        }
        detailRV()
    }

    private fun detailRV() {
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        contactAdapter = DetailAdapter(listOf(contact!!))
        recyclerView.adapter = contactAdapter
    }


    //     좋아요 버튼 이미지 업데이트 함수
    private fun updateLikeButtonImage() {
        if (contact?.like == true) {
            binding.ivLike.setImageResource(R.drawable.ic_heart_filled)
        } else {
            binding.ivLike.setImageResource(R.drawable.ic_heart_outlined)
        }
    }

    private fun updateData(updatedContact: Contact) {
        Log.d("DetailActivity", "updateData called")
        val returnIntent = Intent()
        returnIntent.putExtra(Contact.CONTACT_KEY, updatedContact)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}
//contectlist
//main으로 intent
//resultlauncher




