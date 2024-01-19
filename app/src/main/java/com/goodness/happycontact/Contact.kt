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
	val address: String,
	val relationship: String,
	val bigDay: String,
	val birthDay: String,
	val memo: String

) : Parcelable {
	companion object {
		val DATA = mutableListOf(
			Contact(
				id = 1,
				profileImage = R.drawable.break_sparta,
				name = "파괴하는 르탄이",
				email = "break@sparta.com",
				like = false,
				phoneNumber = "01012341234",
				address = "스파시 르타구 코딩동 클럽번지",
				relationship = "직장상사",
				bigDay = "1.19 회식",
				birthDay = "10월19일",
				memo = "전화 오면 무시"
			),
			Contact(
				id = 2,
				profileImage = R.drawable.chickensparta,
				name = "치킨르탄",
				email = "chicken@sparta.com",
				like = false,
				phoneNumber = "01078782341",
				address = "스파시 르타구 코딩동 클럽번지",
				relationship = "직장동료",
				bigDay = "10.14 프로젝트 진행",
				birthDay = "12월31일",
				memo = " "
			),
			Contact(
				id = 3,
				profileImage = R.drawable.dragon_sparta,
				name = "용탄이",
				email = "dragon@sparta.com",
				like = false,
				phoneNumber = "01032121234",
				address = "스파시 르타구 코딩동 클럽번지",
				relationship = "직장동료",
				bigDay = "10.01 입사",
				birthDay = "6월20일",
				memo = "신입"
			),
			Contact(
				id = 4,
				profileImage = R.drawable.horn_sparta,
				name = "뿔난르탄이",
				email = "horn@sparta.com",
				like = false,
				phoneNumber = "01088888888",
				address = "스파시 르타구 코딩동 클럽번지",
				relationship = "친구",
				bigDay = "7.14 일본여행",
				birthDay = "7월14일",
				memo = "베프"
			),
			Contact(
				id = 5,
				profileImage = R.drawable.monkey_sparta,
				name = "원르탄이",
				email = "monkey@sparta.com",
				like = false,
				phoneNumber = "01064646464",
				address = "스파시 르타구 코딩동 클럽번지",
				relationship = "친구",
				bigDay = "1.20 약속",
				birthDay = "3월26일",
				memo = "약속 시간 안지킴"
			),
			Contact(
				id = 6,
				profileImage = R.drawable.movie_sparta,
				name = "영화보는 르탄이",
				email = "moviegood@sparta.com",
				like = false,
				phoneNumber = "01081238123",
				address = "스파시 르타구 코딩동 클럽번지",
				relationship = "코딩 스터디 일원",
				bigDay = "1.24 스터디 진행",
				birthDay = "12월01일",
				memo = "스터디 일원"
			),
			Contact(
				id = 7,
				profileImage = R.drawable.rabbit_sparta,
				name = "토끼르탄",
				email = "rabbit@sparta.com",
				like = false,
				phoneNumber = "01011111111",
				address = "스파시 르타구 코딩동 클럽번지",
				relationship = "코딩 스터디 조장",
				bigDay = "1.24 스터디 진행",
				birthDay = "10월20일",
				memo = "스터디 조장"
			),
			Contact(
				id = 8,
				profileImage = R.drawable.run_sparta,
				name = "도망가는 르탄이",
				email = "runsparta@sparta.com",
				like = false,
				phoneNumber = "01066666666",
				address = "스파시 르타구 코딩동 클럽번지",
				relationship = "코딩 스터디 일원",
				bigDay = "1.24 스터디 진행",
				birthDay = "3월8일",
				memo = " "
			),
			Contact(
				id = 9,
				profileImage = R.drawable.sheep_starta,
				name = "양탄이",
				email = "sheep@sparta.com",
				like = false,
				phoneNumber = "01077777777",
				address = "스파시 르타구 코딩동 클럽번지",
				relationship = "코딩 스터디 일원",
				bigDay = "1.24 스터디 진행",
				birthDay = "1월5일",
				memo = "전화 오면 무시"
			),
			Contact(
				id = 10,
				profileImage = R.drawable.snake_sparta,
				name = "뱀탄",
				email = "snake@sparta.com",
				like = false,
				phoneNumber = "01043214321",
				address = "스파시 르타구 코딩동 클럽번지",
				relationship = "스파르타 매니저님",
				bigDay = "1.23 발제",
				birthDay = "7월3일",
				memo = " "
			),
			Contact(
				id = 11,
				profileImage = R.drawable.sparta,
				name = "그냥르탄",
				email = "sparta@sparta.com",
				like = false,
				phoneNumber = "01000001234",
				address = "스파시 르타구 코딩동 클럽번지",
				relationship = "스파르타 매니저님",
				bigDay = " ",
				birthDay = "4월13일",
				memo = "전화 바로 받기"
			),
			Contact(
				id = 12,
				profileImage = R.drawable.sparta2,
				name = "행복한 르탄",
				email = "happy@sparta.com",
				like = false,
				phoneNumber = "01043214321",
				address = "스파시 르타구 코딩동 클럽번지",
				relationship = "스파르타 튜터님",
				bigDay = " ",
				birthDay = "2월8일",
				memo = "오전에만 계심"
			),
			Contact(
				id = 13,
				profileImage = R.drawable.sparta3,
				name = "개탄",
				email = "dogtan@sparta.com",
				like = false,
				phoneNumber = "01099999999",
				address = "스파시 르타구 코딩동 클럽번지",
				relationship = "스파르타 튜터님",
				bigDay = "1.14 줌 수업",
				birthDay = "6월13일",
				memo = "챌린지 튜터님"
			),
			Contact(
				id = 14,
				profileImage = R.drawable.tiger_sparta,
				name = "호랑탄",
				email = "tiger@sparta.com",
				like = false,
				phoneNumber = "01012343213",
				address = "스파시 르타구 코딩동 클럽번지",
				relationship = "스파르타 튜터님",
				bigDay = "1.18 피드백",
				birthDay = "12월15일",
				memo = "스탠다드 튜터님"
			),
			Contact(
				id = 15,
				profileImage = R.drawable.warrior_sparta,
				name = "전탄",
				email = "warrior@sparta.com",
				like = false,
				phoneNumber = "01011112222",
				address = "스파시 르타구 코딩동 클럽번지",
				relationship = "스파르타 튜터님",
				bigDay = "1.14 줌 수업",
				birthDay = "8월4일",
				memo = "베이직 튜터님"
			),
		).sortedBy { it.name }.toMutableList()

		val CONTACT_KEY = "CONTACT"
	}
}

interface ItemClick

// Contact.DATA해서 데이터 가져다가 쓰세요*/