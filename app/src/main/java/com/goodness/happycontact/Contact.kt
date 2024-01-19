package com.goodness.happycontact

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class Select : Parcelable {

	data class Title(val nmValue: String) : Select()
}

@Parcelize
data class Contact(
	val id: Int,
	val profileImage: Int,
	val profileImageUri: String? = null,
	val name: String,
	val email: String,
	val phoneNumber: String,
	var like: Boolean,
//	val address: String,
//	val relationship: String,
//	val bigDay: String,
//	val birthDay: String
//	val memo: String

) : Parcelable {
	companion object {
		val DATA = mutableListOf(
			Contact(
				id = 1,
				profileImage = R.drawable.break_sparta,
				name = "파괴하는 르탄이",
				email = "break@sparta.com",
				like = false,
				phoneNumber = "01012341234"
			),
			Contact(
				id = 2,
				profileImage = R.drawable.chickensparta,
				name = "치킨르탄",
				email = "chicken@sparta.com",
				like = false,
				phoneNumber = "01078782341"
			),
			Contact(
				id = 3,
				profileImage = R.drawable.dragon_sparta,
				name = "용탄이",
				email = "dragon@sparta.com",
				like = false,
				phoneNumber = "01032121234"
			),
			Contact(
				id = 4,
				profileImage = R.drawable.horn_sparta,
				name = "뿔난르탄이",
				email = "horn@sparta.com",
				like = false,
				phoneNumber = "01088888888"
			),
			Contact(
				id = 5,
				profileImage = R.drawable.monkey_sparta,
				name = "원르탄이",
				email = "monkey@sparta.com",
				like = false,
				phoneNumber = "01064646464"
			),
			Contact(
				id = 6,
				profileImage = R.drawable.movie_sparta,
				name = "영화보는 르탄이",
				email = "moviegood@sparta.com",
				like = false,
				phoneNumber = "01081238123"
			),
			Contact(
				id = 7,
				profileImage = R.drawable.rabbit_sparta,
				name = "토끼르탄",
				email = "rabbit@sparta.com",
				like = false,
				phoneNumber = "01011111111"
			),
			Contact(
				id = 8,
				profileImage = R.drawable.run_sparta,
				name = "도망가는 르탄이",
				email = "runsparta@sparta.com",
				like = false,
				phoneNumber = "01066666666"
			),
			Contact(
				id = 9,
				profileImage = R.drawable.sheep_starta,
				name = "양탄이",
				email = "sheep@sparta.com",
				like = false,
				phoneNumber = "01077777777"
			),
			Contact(
				id = 10,
				profileImage = R.drawable.snake_sparta,
				name = "뱀탄",
				email = "snake@sparta.com",
				like = false,
				phoneNumber = "01043214321"
			),
			Contact(
				id = 11,
				profileImage = R.drawable.sparta,
				name = "그냥르탄",
				email = "sparta@sparta.com",
				like = false,
				phoneNumber = "01000001234"
			),
			Contact(
				id = 12,
				profileImage = R.drawable.sparta2,
				name = "행복한 르탄",
				email = "happy@sparta.com",
				like = false,
				phoneNumber = "01043214321"
			),
			Contact(
				id = 13,
				profileImage = R.drawable.sparta3,
				name = "개탄",
				email = "dogtan@sparta.com",
				like = false,
				phoneNumber = "01099999999"
			),
			Contact(
				id = 14,
				profileImage = R.drawable.tiger_sparta,
				name = "호랑탄",
				email = "tiger@sparta.com",
				like = false,
				phoneNumber = "01012343213"
			),
			Contact(
				id = 15,
				profileImage = R.drawable.warrior_sparta,
				name = "전탄",
				email = "warrior@sparta.com",
				like = false,
				phoneNumber = "01011112222"
			),
		).sortedBy { it.name }.toMutableList()

		val CONTACT_KEY = "CONTACT"
	}
}

interface ItemClick

// Contact.DATA해서 데이터 가져다가 쓰세요*/