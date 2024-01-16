package com.goodness.happycontact

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.goodness.happycontact.databinding.ActivityMainBinding
import de.hdodenhof.circleimageview.CircleImageView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
	private val dataList = mutableListOf<Contact>()


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

		binding.btnTest.setOnClickListener {
			val builder = AlertDialog.Builder(this)
			builder.setTitle("Add contact")

			val view1 = layoutInflater.inflate(R.layout.add_contact_dialog, null)
			builder.setView(view1)

			val listener = DialogInterface.OnClickListener { dialog, _ ->
				val alert = dialog as AlertDialog

//				val rep1: CircleImageView? = alert.findViewById(R.id.editImg) // 이미지 선택 구현 후 부여 필요함..1
				val rep1 = findViewById<CircleImageView>(R.id.editImg)
				val rep2: EditText? = alert.findViewById(R.id.editName)
				val rep3: EditText? = alert.findViewById(R.id.editNum)
				val rep4: EditText? = alert.findViewById(R.id.editMail)

//				val img = findViewById<CircleImageView>(R.id.editImg) // 이미지 선택 구현 후 부여 필요함..2
				val img = rep1.toString().toInt()
				val name = rep2?.text.toString()
				val phoneNumber = rep3?.text.toString()
				val email = rep4?.text.toString()

				dataList.add(Contact(dataList.size + 1, img, name, email, phoneNumber, false))
			}

			builder.setPositiveButton("추가") { dialog, _ ->
				// 버튼 클릭시 실행될 로직
				listener
				Validation(view1, dialog)
			}

			builder.setNegativeButton("취소", null)
			builder.show()
		}
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

	private fun Validation(dialogView: View, dialog: DialogInterface) {
		val rep2: EditText? = dialogView.findViewById(R.id.editName)
		val rep3: EditText? = dialogView.findViewById(R.id.editNum)
		val rep4: EditText? = dialogView.findViewById(R.id.editMail)

		val name = rep2?.text.toString()
		val phoneNumber = rep3?.text.toString()
		val email = rep4?.text.toString()

		if (name.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
			Toast.makeText(this, "1개 이상의 정보가 입력되지 않았습니다", Toast.LENGTH_SHORT).show()
		} else {
			//적용이 안됨
		}
	}

}