package com.goodness.happycontact

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.goodness.happycontact.databinding.AddContactDialogBinding
import android.util.Patterns
import android.view.WindowManager
import androidx.activity.result.contract.ActivityResultContracts

class AddFrag(val onAdd: () -> Unit) : DialogFragment() {
	private var _binding: AddContactDialogBinding? = null
	private val binding get() = _binding!!
	//_binding이 null이 아닌 경우에 뷰 바인딩 객체를 전달
	private var selectedImageUri: Uri? = null
	//이미지를 선택한 경우 이미지 주소를 보관할 공간이고, 이미지를 선택하지 않을 경우에는 기본 이미지를 사용하므로 nullable 설정

	private val pickImageFromGallery = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? -> uri?.let {
		//아래 setOnClickListener와 함께 사용되는데 registerForActivityResult는 액티비티 결과를 콜백하는 메소드
		//ActivityResultContracts.GetContent는 Listener에서 실행한 "image/*"에서 컨텐츠를 선택
		//uri: Uri? -> uri?.let, uri가 null이 될 수 있으며 null이 아닌경우 {}내용을 실행
			binding.editImg.setImageURI(uri)
			//선택한 이미지를 id:editImg 이미지 뷰에 설정
			selectedImageUri = uri
			//선택한 이미지를 변수 selectedImageUri에 저장
		}
	}

	override fun onStart() {
		super.onStart()
		dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
		//다이얼로그 onStart시 키보드가 실행되면 다이얼로그를 가리게 되는데 WindowManager로 레이아웃의 크기를 키보드 위로 조정
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = AddContactDialogBinding.inflate(inflater, container, false)
		//프래그먼트의 뷰를 실행
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		//{}코드를 프래그먼트 뷰가 완전히 생선된 후 실행

		binding.editImg.setOnClickListener {
			pickImageFromGallery.launch("image/*")
		}
		//pickImageFromGallery와 함께 사용되며 이미지를 선택하기 위해 갤러리르 실행

		binding.viewMore.setOnClickListener {
			binding.viewMore.visibility = View.GONE
			binding.editGroup.visibility = View.VISIBLE
			binding.editAdress.visibility = View.VISIBLE
			binding.editBirth.visibility = View.VISIBLE
			binding.editEvent.visibility = View.VISIBLE
			binding.editMemo.visibility = View.VISIBLE
		}
		//id:viewMore 더 보기를 클리하면 visibility gone으로 숨겨둔 입력 필드를 visibility visible로 변경

		binding.btnDialogYes.setOnClickListener {
			//img는 selectedImageUri 에서 Uri 가져와서 사용
			val name = binding.editName.text.toString()
			val num = binding.editNum.text.toString()
			val mail = binding.editMail.text.toString()
			val adr = binding.editAdress.text.toString()
			val group = binding.editGroup.text.toString()
			val birth = binding.editBirth.text.toString()
			val event = binding.editEvent.text.toString()
			val memo = binding.editMemo.text.toString()
			//id:btnDialogYes 추가 버튼을 누르면 입력된 내용을 String 변수로 초기화

			if (name.isEmpty() || num.isEmpty() || mail.isEmpty()) {
				Toast.makeText(context, "모든 필드를 채워주세요", Toast.LENGTH_SHORT).show()
//				return@setOnClickListener //아래 코드 실행하지 않고 처음으로 감
			}
			//입력 조건 검사 : 입력 필드 빈칸 확인
			//return@setOnClickListener으로 조건을 만족하지 않은 경우 아래 코드를 실행하지 if문(람다 표현식 이라고 함)을 빠져나감
			//return 입력시 에러가 발생해서 사용할 수 없음

			if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
				Toast.makeText(context, "이메일 형식이 올바르지 않습니다", Toast.LENGTH_SHORT).show()
				return@setOnClickListener //아래 코드 실행하지 않고 처음으로 감
			}
			//입력 조건 검사 : 입력 필드 이메일 형식 확인
			//return@setOnClickListener으로 조건을 만족하지 않은 경우 아래 코드를 실행하지 않고 처음으로 이동

			val addNewContact = Contact(
				id = Contact.DATA.size + 1,
				profileImage = R.drawable.ic_person, // 임시 이미지 추가
				profileImageUri = selectedImageUri?.toString(),
				name = name,
				email = mail,
				phoneNumber = num,
				like = false,
				address = adr,
				relationship = group,
				bigDay = event,
				birthDay = birth,
				memo = memo
			)
			//editImg.setOnClickListener와 btnDialogYes.setOnClickListener로 가져온 데이터를 Contact data class에 전달하기 위해 addNewContact 초기화

			Contact.DATA.add(addNewContact)
			//addNewContact를 Contact.DATA data class list에 추가
			onAdd()

			Toast.makeText(requireContext(), "${name}님의 연락처가 추가되었습니다", Toast.LENGTH_SHORT).show()
			dismiss()
			//연락처 추가 후 토스트로 알리고 다이얼로그 종료
		}
		binding.btnDialogNo.setOnClickListener {
			dismiss()
		}
		//id:btnDialogNo 취소 버튼 클릭 시 다이얼로그 종료
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
	//다이얼로그 뷰가 종료되면 View Binding 참조 해제, 메모리 누수 방지를 위해 사용

}