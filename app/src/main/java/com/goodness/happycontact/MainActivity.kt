package com.goodness.happycontact

import android.content.DialogInterface
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.goodness.happycontact.databinding.ActivityMainBinding
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {
	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)






		binding.btnTest.setOnClickListener {
			val builder = AlertDialog.Builder(this)
			builder.setTitle("Add contact")
//			builder.setIcon(R.mipmap.ic_launcher)

			val v1 = layoutInflater.inflate(R.layout.add_contact_dialog, null)
			builder.setView(v1)

			// p0에 해당 AlertDialog가 들어온다. findViewById를 통해 view를 가져와서 사용
			val listener = DialogInterface.OnClickListener { p0, p1 ->
				val alert = p0 as AlertDialog

				val rep1: CircleImageView? = alert.findViewById<CircleImageView>(R.id.editImg)
				val rep2: EditText? = alert.findViewById<EditText>(R.id.editName)
				val rep3: EditText? = alert.findViewById<EditText>(R.id.editNum)
				val rep4: EditText? = alert.findViewById<EditText>(R.id.editMail)

//				binding.tvTitle.text = "사진 : ${rep1?.text}"
//				binding.tvTitle.append("이름 : ${rep2?.text}")
//				binding.tvTitle.append("번호 : ${rep3?.text}")
//				binding.tvTitle.append("메일 : ${rep4?.text}")

			}

			builder.setPositiveButton("추가", listener)
			builder.setNegativeButton("취소", null)

			builder.show()
		}
	}
}