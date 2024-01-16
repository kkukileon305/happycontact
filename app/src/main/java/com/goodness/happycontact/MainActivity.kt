package com.goodness.happycontact

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.goodness.happycontact.databinding.ActivityMainBinding
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {
	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
	private val dataList = mutableListOf<Contact>()


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)



		binding.btnTest.setOnClickListener {
			val builder = AlertDialog.Builder(this)
			builder.setTitle("Add contact")
			// builder.setIcon(R.mipmap.ic_launcher)

			val v1 = layoutInflater.inflate(R.layout.add_contact_dialog, null)
			builder.setView(v1)

			val listener = DialogInterface.OnClickListener { dialog, _ ->
				val alert = dialog as AlertDialog

				val imgView: CircleImageView? = alert.findViewById(R.id.editImg)
				val nameEditText: EditText? = alert.findViewById(R.id.editName)
				val numberEditText: EditText? = alert.findViewById(R.id.editNum)
				val emailEditText: EditText? = alert.findViewById(R.id.editMail)

				// imgReference는 이미지에 대한 참조나 경로를 나타내야 합니다.
				// 예를 들어, 이미지 리소스 ID나 URL 등을 사용할 수 있습니다.
				// 여기서는 예시로 단순 문자열을 사용하고 있습니다.
				val imgReference = "image_reference_here"

				val name = nameEditText?.text.toString()
				val number = numberEditText?.text.toString()
				val email = emailEditText?.text.toString()

//				dataList.add(Contact(dataList.size + 1, imgReference, name, number, email, false))
			}

			builder.setPositiveButton("추가", listener)
			builder.setNegativeButton("취소", null)

			builder.show()
		}



/*		binding.btnTest.setOnClickListener {
			val builder = AlertDialog.Builder(this)
			builder.setTitle("Add contact")
//			builder.setIcon(R.mipmap.ic_launcher)

			val v1 = layoutInflater.inflate(R.layout.add_contact_dialog, null)
			builder.setView(v1)

			// p0에 해당 AlertDialog가 들어온다. findViewById를 통해 view를 가져와서 사용
			val listener = DialogInterface.OnClickListener { p0, p1 ->
				val alert = p0 as AlertDialog

				val rep1: CircleImageView? = alert.findViewById(R.id.editImg)
				val rep2: EditText? = alert.findViewById(R.id.editName)
				val rep3: EditText? = alert.findViewById(R.id.editNum)
				val rep4: EditText? = alert.findViewById(R.id.editMail)

				dataList.add(Contact(dataList.size+1, rep1, "$rep2", "$rep4", "$rep3",false)



//				binding.tvTitle.text = "사진 : ${rep1?.text}"
//				binding.tvTitle.append("이름 : ${rep2?.text}")
//				binding.tvTitle.append("번호 : ${rep3?.text}")
//				binding.tvTitle.append("메일 : ${rep4?.text}")

			}

			builder.setPositiveButton("추가", listener)
			builder.setNegativeButton("취소", null)

			builder.show()
		}*/
	}
}