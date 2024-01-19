package com.goodness.happycontact

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
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
	//수정전 이미지를 저장해 취소를 눌렀을 때 이전의 이미지로 돌아갈수 있게 이전 이미지를 담아놓음
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

		//다이얼로그 띄우기
		binding.ivEdit.setOnClickListener {

			val builder = AlertDialog.Builder(requireContext())
			dialogBinding = MypageEditDialogBinding.inflate(layoutInflater)
			//다이얼로그 들어가는 변경된 이미지Uri
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
			//프로필사진 변경 intent로
			dialogBinding.ivEditProfileImage.setOnClickListener {

				val intent = Intent(Intent.ACTION_PICK)
				intent.type = "image/*"
				startActivityForResult(intent, pickImage)
			}

			//입력한 정보가 비어있지 않을때 변경
			builder.setPositiveButton("변경") { _, _ ->

				binding.ivMyProfileImage.setImageURI(selectedImageUri)
				//변경시에 임시이미지 또한 같이 변경
				tempImageUri =selectedImageUri

				if (editName.text.isNotEmpty()) {
					binding.tvMyName.text = (editName.text).toString()
				}

				if (editEmail.text.isNotEmpty()) {

					val mail = dialogBinding.dialogEditEmail.text.toString()

					if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
						Toast.makeText(context, "이메일 형식이 올바르지 않습니다", Toast.LENGTH_SHORT).show()
						return@setPositiveButton
					} else {
						binding.tvMyEmail.text = (editEmail.text).toString()
					}
				}

//				if (editNumber.text.isNotEmpty()) {
//					binding.tvMyPhoneNum.text = (editNumber.text).toString()
//				}

				if (editNumber.text.isNotEmpty()) {

					val editNum = editNumber.text.toString()
					// 11자리 일때
					if (editNum.length == 11) {
						val strBuilder = StringBuilder(editNum)
						//3번째자리 ,8번째 자리에 - 넣어서 휴대폰번호 형식 구분하기
						strBuilder.insert(3, "-").insert(8, "-")
						val text = strBuilder.toString()
						binding.tvMyPhoneNum.apply {
							this.text = text
							this.textSize = 24f
							this.setTextColor(Color.BLACK)
						}
					} else {
						binding.tvMyPhoneNum.apply {
							this.text = "번호를 잘못 입력 하셨습니다. 다시 입력해주세요"
							this.textSize = 16F
							this.setTextColor(Color.RED)
						}

					}
				}

				//예외처리로 앱 다운현상 막기
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

//			builder.setNegativeButton("취소",null)
			builder.setNegativeButton("취소") { _,_ ->
				//취소 버튼이 눌리면 기존 이미지를 임시 이미지로 되돌리잚
				selectedImageUri = tempImageUri
				null
			}

			builder.show()

		}


	}
	//암시적 intent를 통해 갤러리를 불러오기
	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)

		if (requestCode == pickImage && resultCode == Activity.RESULT_OK && data != null) {
			selectedImageUri = data.data
			dialogBinding.dialogNowProfileImage.setImageURI(selectedImageUri)
		}
	}

}

