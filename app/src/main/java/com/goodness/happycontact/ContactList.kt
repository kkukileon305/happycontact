package com.goodness.happycontact

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodness.happycontact.databinding.FragmentContactListBinding

class ContactList : Fragment() {
	private lateinit var binding: FragmentContactListBinding
	private lateinit var contactListAdapter: ContactListAdapter
	private lateinit var resultLauncher: ActivityResultLauncher<Intent>
	private val detailActivityContract =
		registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
			// 여기에서 DetailActivity에서의 결과 처리를 수행합니다.
			if (result.resultCode == Activity.RESULT_OK) {
				// 결과가 OK일 때의 처리를 수행합니다.
				val updatedContact = result.data?.getParcelableExtra<Contact>(Contact.CONTACT_KEY)
				updatedContact?.let { updateData(it) }
			}
		}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		resultLauncher =
			registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
				// 여기에서 DetailActivity에서의 결과 처리를 수행합니다.
				if (result.resultCode == Activity.RESULT_OK) {
					// 결과가 OK일 때의 처리를 수행합니다.
					val updatedContact =
						result.data?.getParcelableExtra<Contact>(Contact.CONTACT_KEY)
					updatedContact?.let { updateData(it) }
				}
			}
	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		binding = FragmentContactListBinding.inflate(inflater, container, false)

		val recyclerView = binding.rvContactList

		contactListAdapter = ContactListAdapter(requireContext(), Contact.DATA) { position ->
			val clickedData = Contact.DATA[position]
			clickedData.like = !clickedData.like
			updateData(clickedData)
			resultLauncher
		}

		recyclerView.adapter = contactListAdapter
		recyclerView.layoutManager = LinearLayoutManager(requireContext())

		return binding.root

	}

	fun notifyContactInsertedData() {
		contactListAdapter.notifyItemInserted(Contact.DATA.size - 1)
	}

	//	fun updateData(updatedContact: Contact) {
//		if (!::contactListAdapter.isInitialized) {
//			// 초기화되지 않았다면 아무것도 수행하지 않고 종료
//			return
//		}
//		val position = Contact.DATA.indexOfFirst { it.id == updatedContact.id }
//		if (position != -1) {
//			Contact.DATA[position] = updatedContact
//			contactListAdapter.notifyItemChanged(position)
//		}
//	}
	private fun updateData(updatedContact: Contact) {
		if (!::contactListAdapter.isInitialized) {
			// 초기화되지 않았다면 아무것도 수행하지 않고 종료
			return
		}
		val position = Contact.DATA.indexOfFirst { it.id == updatedContact.id }
		if (position != -1) {
			Contact.DATA[position] = updatedContact
			contactListAdapter.notifyItemChanged(position)
		}
	}
}
