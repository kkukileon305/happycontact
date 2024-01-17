package com.goodness.happycontact

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.goodness.happycontact.databinding.FragmentContactListBinding
import com.goodness.happycontact.databinding.FragmentMyPageBinding
import com.goodness.happycontact.databinding.MypageEditDialogBinding
import java.text.DecimalFormat

class MyPage : Fragment() {
	private lateinit var binding: FragmentMyPageBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentMyPageBinding.inflate(inflater, container, false)
		return binding.root
	}


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.ivEdit.setOnClickListener {
			val builder = AlertDialog.Builder(requireContext())


			val dialogBinding = MypageEditDialogBinding.inflate(layoutInflater)
			builder.setView(dialogBinding.root)
			val editName: EditText = dialogBinding.dialogEditName
			val editEmail: EditText = dialogBinding.dialogEditEmail
			val editNumber: EditText = dialogBinding.dialogEditNumber
			val profileImage : ImageView = dialogBinding.dialogNowProfileImage

			dialogBinding.ivEditProfileImage.setOnClickListener {
				val intent = Intent(Intent.ACTION_PICK)
				intent.type = "image/*"
				startActivity(intent)
			}



			//현재 설정한 이름 다이얼로그 Edithint에 띄우기
			editName.hint = (binding.tvMyName).text
			editEmail.hint = (binding.tvMyEmail).text
			editNumber.hint = (binding.tvMyPhoneNum).text

			builder.setPositiveButton("변경") { _,_ ->



				if (editName.text.isNotEmpty()) {
					binding.tvMyName.text = (editName.text).toString()
				}
				if (editEmail.text.isNotEmpty()) {
					binding.tvMyEmail.text = (editEmail.text).toString()
				}
				if (editNumber.text.isNotEmpty()) {
					binding.tvMyPhoneNum.text = (editNumber.text).toString()

				}
			}

			builder.setNegativeButton("취소",null)

			builder.show()

		}

	}

}