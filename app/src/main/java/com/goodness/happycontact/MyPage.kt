package com.goodness.happycontact

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.goodness.happycontact.databinding.FragmentMyPageBinding
import com.goodness.happycontact.databinding.MypageEditDialogBinding
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Locale




class MyPage : Fragment() {
	private lateinit var binding: FragmentMyPageBinding
	private lateinit var dialogBinding: MypageEditDialogBinding
	private val PICK_IMAGE_REQUEST = 1



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
			dialogBinding = MypageEditDialogBinding.inflate(layoutInflater)


			val editName: EditText = dialogBinding.dialogEditName
			val editEmail: EditText = dialogBinding.dialogEditEmail
			val editNumber: EditText = dialogBinding.dialogEditNumber
			val editBirth : EditText = dialogBinding.dialogEditBirthDay

			//현재 설정한 정보 다이얼로그 Edithint에 띄우기
			editName.hint = (binding.tvMyName).text
			editEmail.hint = (binding.tvMyEmail).text
			editNumber.hint = (binding.tvMyPhoneNum).text

			builder.setView(dialogBinding.root)

			dialogBinding.ivEditProfileImage.setOnClickListener {
				val intent = Intent(Intent.ACTION_PICK)
				intent.type = "image/*"
				startActivityForResult(intent, PICK_IMAGE_REQUEST)
			}

//			val numFormat = DecimalFormat("###-####-####")
//			val dateFormat = DecimalFormat("####/##/##")
//			val rawPhoneNumber = (binding.tvMyPhoneNum.text).toString()
//			val formattingNum = numFormat.format(rawPhoneNumber)


			builder.setPositiveButton("변경") { _, _ ->

				if (editName.text.isNotEmpty()) {
					binding.tvMyName.text = (editName.text).toString()
				}
				if (editEmail.text.isNotEmpty()) {
					binding.tvMyEmail.text = (editEmail.text).toString()
				}
				if (editNumber.text.isNotEmpty()) {
					binding.tvMyPhoneNum.text = (editNumber.text).toString()
				}
				try {
					if (editBirth.text.isNotEmpty()) {
						val enterDate = (editBirth.text).toString()
						val dateFormat = if (enterDate.contains("/")) {
							SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
						} else {
							SimpleDateFormat("yyyyMMdd", Locale.getDefault())
						}
						val parsedDate = dateFormat.parse(enterDate)

						if (parsedDate != null) {
							val formattedDate = dateFormat.format(parsedDate)
							binding.tvMyBirthDay.text = formattedDate.toString()
						}

					}
				} catch (e: Exception){
					e.printStackTrace()
				}
			}

			builder.setNegativeButton("취소",null)

			builder.show()

		}


	}
	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)

		if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
			val selectedImageUri = data.data

			binding.ivMyProfileImage.setImageURI(selectedImageUri)
			dialogBinding.dialogNowProfileImage.setImageURI(selectedImageUri)
		}
	}


}

