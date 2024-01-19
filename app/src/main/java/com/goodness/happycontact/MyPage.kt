package com.goodness.happycontact

import android.app.Activity
import android.content.Intent
import android.net.Uri
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
import kotlin.text.StringBuilder


class MyPage : Fragment() {

	private lateinit var binding: FragmentMyPageBinding
	private lateinit var dialogBinding: MypageEditDialogBinding
	private val pickImage = 1
	private var selectedImageUri: Uri? = null
	private var tempImageUri : Uri? = null




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

			if (selectedImageUri != null) {
				dialogBinding.dialogNowProfileImage.setImageURI(selectedImageUri)
			}

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
				startActivityForResult(intent, pickImage)
			}


			builder.setPositiveButton("변경") { _, _ ->

				binding.ivMyProfileImage.setImageURI(selectedImageUri)
				tempImageUri =selectedImageUri

				if (editName.text.isNotEmpty()) {
					binding.tvMyName.text = (editName.text).toString()
				}
				if (editEmail.text.isNotEmpty()) {
					binding.tvMyEmail.text = (editEmail.text).toString()
				}

//				if (editNumber.text.isNotEmpty()) {
//					binding.tvMyPhoneNum.text = (editNumber.text).toString()
//				}

				if (editNumber.text.isNotEmpty()) {

					val editNum = editNumber.text.toString()
					val strBuilder = StringBuilder(editNum)

					strBuilder.insert(3,"-").insert(8,"-")
					val text = strBuilder.toString()
					binding.tvMyPhoneNum.text = text
				}


				try {

					if (editBirth.text.isNotEmpty()) {
						val enterDate = (editBirth.text).toString()
						val dateFormat = if (enterDate.contains("/")) {
							SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
						} else {
							SimpleDateFormat("yyyy MM dd", Locale.getDefault())
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

//			builder.setNegativeButton("취소",null)//
			builder.setNegativeButton("취소") { _,_ ->
				selectedImageUri = tempImageUri
				null
			}

			builder.show()

		}


	}
	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)

		if (requestCode == pickImage && resultCode == Activity.RESULT_OK && data != null) {
			selectedImageUri = data.data
			dialogBinding.dialogNowProfileImage.setImageURI(selectedImageUri)
		}
	}

}

