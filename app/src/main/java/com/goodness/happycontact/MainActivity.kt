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
import com.goodness.happycontact.databinding.AddContactDialogBinding
import de.hdodenhof.circleimageview.CircleImageView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		addButtonPressed()
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

	private fun addButtonPressed(){
		binding.btnTest.setOnClickListener {
			AddFrag().show(supportFragmentManager,"AddFrag")

		}
	}



/*	private fun Validation(dialogView: View, dialog: DialogInterface) {
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
	}*/

}