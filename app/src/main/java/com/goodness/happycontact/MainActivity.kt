package com.goodness.happycontact

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.goodness.happycontact.databinding.ActivityMainBinding
import com.goodness.happycontact.databinding.AddContactDialogBinding
import de.hdodenhof.circleimageview.CircleImageView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
	private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(binding.root)

		addButtonPressed()
		setSwiper()
	}

	private fun addButtonPressed(){
		binding.btnTest.setOnClickListener {
			AddFrag().show(supportFragmentManager,"AddFrag")

		}
	}

    private fun setSwiper() {
        val viewPager = binding.pager
        val tabLayout = binding.tabLayout

        viewPager.adapter = MainAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.main_tab_name_1)
                1 -> getString(R.string.main_tab_name_2)
                else -> ""
            }
        }.attach()
    }
}
