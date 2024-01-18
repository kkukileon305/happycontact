package com.goodness.happycontact

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.goodness.happycontact.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
	private val adapter by lazy { MainAdapter(this) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		setSwiper()
		addButtonPressed()

		requestContactPermission()
	}

	private fun addButtonPressed() {
		binding.btnTest.setOnClickListener {
			AddFrag {
				val listFragment = supportFragmentManager.findFragmentByTag("f0") as ContactList

				listFragment.updateData()
			}.show(supportFragmentManager, "AddFrag")
		}
	}

	private fun setSwiper() {
		val viewPager = binding.pager
		val tabLayout = binding.tabLayout

		viewPager.adapter = adapter

		TabLayoutMediator(tabLayout, viewPager) { tab, position ->
			tab.text = when (position) {
				0 -> getString(R.string.main_tab_name_1)
				1 -> getString(R.string.main_tab_name_2)
				else -> ""
			}
		}.attach()
	}

	//임시!! 권한허용 확인
	private fun requestContactPermission() {
		val getList = mutableListOf<String>()
		//필요한 권한을 담아 둘 변경 가능한 리스트를 만들고

		if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
			getList.add(android.Manifest.permission.CALL_PHONE)
		}
		if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
			getList.add(android.Manifest.permission.SEND_SMS)
		}
		if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
			getList.add(android.Manifest.permission.CAMERA)
		}
		if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			getList.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
		}
		if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
			getList.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
		}
		//여기서 각 권한이 부여되어 있는지 체크하고, 없으면 getList에 추가함
		//저장공간 권한이 요청되지 않는데 API 29 부터는 Scoped Storage라는 기능을 사용한다고 함 적용 필요

		if (getList.isNotEmpty()) {
			ActivityCompat.requestPermissions(this, getList.toTypedArray(), 1000)
		}
		//여기서 getList의 필요 권한을 가져와 요청한다

		/*
		권한 요청 안드로이드 개발 가이드 https://developer.android.com/training/permissions/requesting?hl=ko
		API30 부터는 사용자가 앱이 기기에 설치된 전체 기간 동안 특정 권한에 관해 거부를 두 번 이상 탭하면 앱에서 그 권한을 다시 요청하는 경우 사용자에게 시스템 권한 대화상자가 표시되지 않도록 설계되어 있음
		따라서 단위기간 동안 권한을 요청하는 기회가 2번 있기 때문에 추후 해당 기능을 사용하려 할 때 권한을 요청하는 방식으로 전환을 고려해야 할 듯
		예전 어플리케이션의 경우 앱 시작과 동시에 권한을 요구하고 권한을 허용하지 않을 경우 앱을 사용하지 못하도록 하는 경우가 많았는데, 아래와 같이 가이드에는 인터페이스를 차단하지 않도록 권장하고 있음

		[ 사용자 인터페이스를 차단하지 않습니다. 즉, 사용자가 앱을 계속 사용하는 것을 막는 전체 화면 경고 메시지를 표시하지 않습니다. ]
		*/
	}





}
