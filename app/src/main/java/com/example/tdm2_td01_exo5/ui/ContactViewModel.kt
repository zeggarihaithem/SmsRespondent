package com.example.tdm2_td01_exo5.ui

import android.app.Application
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tdm2_td01_exo5.data.entity.Contact
import com.example.tdm2_td01_exo5.data.repository.ContactRepository

class ContactViewModel(@NonNull application: Application?) : ViewModel() {
    private val repository: ContactRepository = ContactRepository(application!!)
    private val allContact: List<Contact>

    fun getAllContact() : List<Contact>{
        return allContact
    }

    init {
        allContact = repository.getContact()
    }
}