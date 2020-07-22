package com.example.tdm2_td01_exo5.data.repository

import android.app.Application
import com.example.tdm2_td01_exo5.data.contentProvider.ContactProvider
import com.example.tdm2_td01_exo5.data.entity.Contact

class ContactRepository(private val application: Application) {
    private var contactProvider: ContactProvider = ContactProvider(application)
    private lateinit var listContact: List<Contact>


    fun getContact() : List<Contact>{
        return listContact
    }
    init {
        listContact = contactProvider.getListContact()
    }


}