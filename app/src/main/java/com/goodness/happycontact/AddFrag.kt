package com.goodness.happycontact

import android.content.Context
import android.graphics.Point
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.goodness.happycontact.databinding.AddContactDialogBinding
import android.util.Patterns
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts

class AddFrag(val handler: () -> Unit) : DialogFragment() {
	private var _binding: AddContactDialogBinding? = null
	private val binding get() = _binding!!
	private var selectedImageUri: Uri? = null
	//이미지를 선택한 경우 이미지 주소를 보관해야 함

	private val pickImageFromGallery = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
		uri?.let {
			binding.editImg.setImageURI(uri)
			selectedImageUri = uri
		}
	}

	override fun onResume() { //dialog view 가로 크기 비율을 설정 : 코드 가져옴
		val windowManager = requireContext().getSystemService(Context.WINDOW_SERVICE) as WindowManager
		val display = windowManager.defaultDisplay
		val size = Point()
		display.getSize(size)

		val params: ViewGroup.LayoutParams? = dialog?.window?.attributes
		val deviceWidth = size.x
		params?.width = (deviceWidth * 0.9).toInt()
		dialog?.window?.attributes = params as WindowManager.LayoutParams
		super.onResume()
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = AddContactDialogBinding.inflate(inflater, container, false)
		Log.d("행동확인", "다이얼로그 생성")
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.editImg.setOnClickListener {
			pickImageFromGallery.launch("image/*")
		}

		binding.btnDialogYes.setOnClickListener {
			//img는 selectedImageUri 에서 Uri 가져와서 사용
			val name = binding.editName.text.toString()
			val num = binding.editNum.text.toString()
			val mail = binding.editMail.text.toString()

			if (name.isEmpty() || num.isEmpty() || mail.isEmpty()) {
				Toast.makeText(context, "모든 필드를 채워주세요", Toast.LENGTH_SHORT).show()
				return@setOnClickListener //아래 코드 실행하지 않고 처음으로 감
			}

			if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
				Toast.makeText(context, "이메일 형식이 올바르지 않습니다", Toast.LENGTH_SHORT).show()
				return@setOnClickListener //아래 코드 실행하지 않고 처음으로 감
			}

			val addNewContact = Contact(
				id = Contact.DATA.size + 1,
				profileImage = R.drawable.ic_launcher_foreground, // 임시 이미지 추가
				profileImageUri = selectedImageUri.toString(),
				name = name,
				email = mail,
				phoneNumber = num,
				like = false
			)

			Contact.DATA.add(addNewContact)
			handler()

			Toast.makeText(requireContext(), "${name}님의 연락처가 추가되었습니다", Toast.LENGTH_SHORT).show()
			dismiss()
		}
		binding.btnDialogNo.setOnClickListener {
			dismiss()
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

}