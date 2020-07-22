package com.example.tdm2_td01_exo5.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tdm2_td01_exo5.R
import com.example.tdm2_td01_exo5.adapter.ContactAdapter
import com.example.tdm2_td01_exo5.data.entity.Contact
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private lateinit var viewModel: ContactViewModel
    private lateinit var viewModelFactory: ContactViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (checkPermission(Manifest.permission.READ_CONTACTS)) {

            //set recycle view adapter
            val recyclerView: RecyclerView = recycler_view as RecyclerView
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.setHasFixedSize(true)

            val adapter = ContactAdapter()
            recyclerView.adapter = adapter

            //set ViewModel
            viewModelFactory = ContactViewModelFactory(this.application)
            viewModel =
                ViewModelProviders.of(this, viewModelFactory).get(ContactViewModel::class.java)
            adapter.setContacts(viewModel.getAllContact())

            //send sms on click
            adapter.setOnItemClickListener(object : ContactAdapter.OnItemClickListener {
                override fun onItemClick(contact: Contact?) {
                    if (checkPermission(Manifest.permission.SEND_SMS)) {
                        val smsManager: SmsManager = SmsManager.getDefault()
                        smsManager.sendTextMessage(
                            contact?.number,
                            null,
                            "This is SMS from SPAM Mobile application",
                            null,
                            null
                        )
                        Toast.makeText(
                            this@MainActivity,
                            "SMS send to ${contact?.name}",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "You don't have permission to send SMS",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })

        }
    }

    private fun checkPermission(permission: String): Boolean {
        val check = ContextCompat.checkSelfPermission(this, permission)
        return (check == PackageManager.PERMISSION_GRANTED)
    }
}
