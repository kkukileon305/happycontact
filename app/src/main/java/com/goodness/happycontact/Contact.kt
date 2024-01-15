package com.goodness.happycontact

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
	val profileImage: Int,
	val name: String,
	val email: String,
	val phoneNumber: String,
	val like: Boolean
) : Parcelable