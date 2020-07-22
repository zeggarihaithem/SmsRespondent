package com.example.tdm2_td01_exo5.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *  This class is for passing  the Contact Repository to the MainActivity
 *  when we create the instance of the VideoViewModel
 */

@Suppress("UNCHECKED_CAST")
open class ContactViewModelFactory(
    private val application: Application
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContactViewModel(
            application
        ) as T
    }

}