package com.xu.test

import android.os.Parcel
import android.os.Parcelable

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/6/4 15:10
 * @version 2.2
 */
class LoginVo() :Parcelable {
    var avatarFiledId: String? = null
    var emailOn: String? = null
    var createOn: String? = null
    var id //customer id
            = 0
    var imei: String? = null
    var isActiveReminder: String? = null
    var tEmail: String? = null
    var tGender: String? = null
    var tNamaApk: String? = null
    var tNoktp: String? = null
    var tTelp: String? = null
    var token: String? = null
    var ktpAuthorized = false

    constructor(parcel: Parcel) : this() {
        avatarFiledId = parcel.readString()
        emailOn = parcel.readString()
        createOn = parcel.readString()
        id = parcel.readInt()
        imei = parcel.readString()
        isActiveReminder = parcel.readString()
        tEmail = parcel.readString()
        tGender = parcel.readString()
        tNamaApk = parcel.readString()
        tNoktp = parcel.readString()
        tTelp = parcel.readString()
        token = parcel.readString()
        ktpAuthorized = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(avatarFiledId)
        parcel.writeString(emailOn)
        parcel.writeString(createOn)
        parcel.writeInt(id)
        parcel.writeString(imei)
        parcel.writeString(isActiveReminder)
        parcel.writeString(tEmail)
        parcel.writeString(tGender)
        parcel.writeString(tNamaApk)
        parcel.writeString(tNoktp)
        parcel.writeString(tTelp)
        parcel.writeString(token)
        parcel.writeByte(if (ktpAuthorized) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginVo> {
        override fun createFromParcel(parcel: Parcel): LoginVo {
            return LoginVo(parcel)
        }

        override fun newArray(size: Int): Array<LoginVo?> {
            return arrayOfNulls(size)
        }
    }
}