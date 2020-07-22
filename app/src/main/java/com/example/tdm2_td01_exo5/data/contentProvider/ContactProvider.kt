package com.example.tdm2_td01_exo5.data.contentProvider

import android.app.Application
import android.content.ContentResolver
import android.database.Cursor
import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import com.example.tdm2_td01_exo5.data.entity.Contact
import java.lang.Exception
import java.lang.Integer.parseInt


@Suppress("UNCHECKED_CAST")
class ContactProvider(private val application: Application) {


    fun getListContact() :  ArrayList<Contact>{
        return providePhoneContact()
    }

    private fun providePhoneContact()  : ArrayList<Contact> {
        val listContact = ArrayList<Contact>()
        var cursor: Cursor? = null
        val contentResolver: ContentResolver = application.contentResolver
        try {
            cursor =
                contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (cursor!!.count > 0) {
            while (cursor.moveToNext()) {
                var number: String = ""
                val id: String =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name: String =
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val hasNumber =
                    parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))
                if (hasNumber > 0) {
                    val phoneCursor: Cursor? =
                        contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?", arrayOf(id), null)
                    while (phoneCursor!!.moveToNext()) {

                        number =
                            phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    }
                    phoneCursor.close()
                }
                listContact.add(Contact(id, name, number))
            }
        }
        cursor.close()
       return listContact
    }



}