package com.example.tdm2_td01_exo5.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tdm2_td01_exo5.R
import com.example.tdm2_td01_exo5.data.entity.Contact

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactHolder>() {
    private var contacts: List<Contact> = ArrayList()
    private var listener:OnItemClickListener? = null
    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ContactHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return ContactHolder(itemView)
    }

    override fun onBindViewHolder(@NonNull holder: ContactHolder, position: Int) {
        val currentContact: Contact = contacts[position]
        holder.textViewId.text = currentContact.id.toString()
        holder.textViewName.text = currentContact.name
        holder.textViewNumber.text = currentContact.number

    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun setContacts(contact: List<Contact>) {
        this.contacts = contact
        notifyDataSetChanged()
    }
    fun getContactAt(position: Int): Contact? {
        return contacts[position]
    }
    inner class ContactHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        internal val textViewId: TextView = itemView.findViewById(R.id.text_view_id)
        internal val textViewName: TextView = itemView.findViewById(R.id.text_view_name)
        internal val textViewNumber: TextView = itemView.findViewById(R.id.text_view_number)

        init {

            itemView.setOnClickListener {
                val position = adapterPosition
                if (listener!= null && position != RecyclerView.NO_POSITION) {
                    listener!!.onItemClick(contacts[position])
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(contact : Contact?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
}
