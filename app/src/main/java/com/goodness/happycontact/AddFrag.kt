package com.goodness.happycontact

import android.content.Context
import android.graphics.Point
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

class AddFrag : DialogFragment() {
    private var _binding: AddContactDialogBinding? = null
    private val binding get() = _binding!!
//    private val dataList = mutableListOf<Contact>()

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
        Log.d("행동확인","다이얼로그 생성")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnDialogYes.setOnClickListener {
//            val img = binding.editImg.toString().toInt() //정상적으로 전달되는지 확인해야 함, 추가 버튼 눌림과 동시에 크래시남 다른 방법 찾아보기
            val name = binding.editName.text.toString()
            val num = binding.editNum.text.toString()
            val mail = binding.editMail.text.toString()

            if (name.isEmpty() || num.isEmpty() || mail.isEmpty()) {
                Toast.makeText(context, "모든 필드를 채워주세요", Toast.LENGTH_SHORT).show()
                Log.d("행동확인","빈칸 없음 확인 통과하지 못함")
                return@setOnClickListener
                //아래 코드 실행하지 않고 처음으로 감
            }
            Log.d("행동확인","빈칸 없음 확인 통과함")


            if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                Toast.makeText(context, "이메일 형식이 올바르지 않습니다", Toast.LENGTH_SHORT).show()
                Log.d("행동확인","이메일 형식 확인 통과하지 못함")
                return@setOnClickListener
                //아래 코드 실행하지 않고 처음으로 감
            }
            Log.d("행동확인","이메일 형식 확인 통과함")


            // >>> 방법 3개 중 1개 사용 가능
            /* //리스트 사용하는 방식과 다름 -> 사용불가
            val addNewContact = Contact(
                id = dataList.size + 1,
                profileImage = R.drawable.ic_launcher_foreground, // 일단 임시 이미지 추가
                name = name,
                email = mail,
                phoneNumber = num,
                like = false
            )
            dataList.add(addNewContact) //Contact형식 그대로 전달*/

            val addNewContact = Contact(
                id = Contact.DATA.size + 1,
                profileImage = R.drawable.ic_launcher_foreground, // 일단 임시 이미지 추가
                name = name,
                email = mail,
                phoneNumber = num,
                like = false
            )
            Contact.DATA.add(addNewContact) //Contact형식 그대로 전달

            //or
            //리스트 사용하는 방식과 다름 -> 사용불가, dataList.add(Contact(dataList.size + 1, img, name, mail, num, false))
            //Contact.DATA.add(Contact(Contact.DATA.size+1, R.drawable.ic_launcher_foreground, name, mail, num, false))
            // <<<
            dismiss()
            Log.d("행동확인","확인 버튼 눌림")
        }

        binding.btnDialogNo.setOnClickListener {
            dismiss()
            Log.d("행동확인","취소 버튼 눌림")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("행동확인","다이얼로그 Destroy")
    }
}



/*
package com.goodness.happycontact

import android.app.Dialog
import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.goodness.happycontact.databinding.AddContactDialogBinding

class AddFrag:DialogFragment() {
    private val binding by lazy { AddContactDialogBinding.inflate(layoutInflater) }
    private val dataList = mutableListOf<Contact>()



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // > binding으로 연결되어 있은 add_contact_dialog를 불러온다
        val showDialog = Dialog(requireContext())
        showDialog.setContentView(binding.root)
        return showDialog
        // <
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAddPressed()
    }

    override fun onResume() { //dialog view 가로 크기 비율을 설정
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

    */
/* 기존 데이터 전달 및 형식 검사
    private fun btnAddPressed() = with(binding) {
        btnDialogYes.setOnClickListener {
            val img = editImg.toString().toInt()
            val name = editName.text.toString()
            val num = editNum.text.toString()
            val email = editMail.text.toString()

            if (name.isEmpty() || num.isEmpty() || email.isEmpty()) {
                Toast.makeText(requireContext(), "1개 이상의 정보가 입력되지 않았습니다", Toast.LENGTH_SHORT).show()
            } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(requireContext(), "유효하지 않은 이메일 형식입니다", Toast.LENGTH_SHORT).show()
            } else {
                dataList.add(Contact(dataList.size + 1, img, name, email, num, false))
                Toast.makeText(requireContext(), "${name}님의 연락처가 추가되었습니다", Toast.LENGTH_SHORT).show()
                dismiss()
            }
        }
    }
    *//*


    private fun btnAddPressed() = with(binding) {
        btnDialogYes.setOnClickListener {
            val img = editImg.toString().toInt()
            val name = editName.toString()
            val email = editMail.toString()
            val num = editNum.toString()
            val data = Contact(dataList.size + 1, img, name, email, num, false)

            dataList.add(data)
            dismiss()
            Log.d("aaaa","버튼 눌림")
        }
    }



}*/
