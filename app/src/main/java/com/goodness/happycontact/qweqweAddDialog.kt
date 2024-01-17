/*
package com.goodness.happycontact

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.goodness.happycontact.databinding.AddContactDialogBinding
import de.hdodenhof.circleimageview.CircleImageView

class AddDialog : DialogFragment() {
    private var _binding: AddContactDialogBinding? = null
    private val binding get() = _binding!!

    private val dataList = mutableListOf<Contact>()
    private var listener: AddItem? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editName.setOnClickListener {
            val name = binding.editName.text.toString()
            val num = binding.editNum.text.toString()
            val mail = binding.editMail.text.toString()

            if (name.isEmpty() || num.isEmpty() || mail.isEmpty()) {
                Toast.makeText(requireContext(), "1개 이상의 정보가 입력되지 않았습니다", Toast.LENGTH_SHORT).show()
            } else {
                addContactData()
            }
        }
    }
    private fun addContactData() = with(binding) {
//        val img = editImg
        val img = editImg.toString().toInt()
        val name = editName.text.toString()
        val num = editNum.text.toString()
        val email = editMail.text.toString()

//        val intent = Contact(dataList.size + 1, img, name, email, num, false)
        dataList.add(Contact(dataList.size + 1, img, name, email, num, false))

//        listener?.add(intent)
        dismiss()
    }

    interface AddItem {
        fun add(contact: Contact)
    }
}*/
