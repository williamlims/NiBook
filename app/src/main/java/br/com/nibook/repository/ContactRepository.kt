package br.com.nibook.repository

import androidx.lifecycle.LiveData
import br.com.nibook.data.Contact
import br.com.nibook.data.ContactDAO

class ContactRepository(private val contactDAO: ContactDAO) {
    suspend fun insert(contact: Contact){
        contactDAO.insert(contact)
    }

    suspend fun update(contact: Contact){
        contactDAO.update(contact)
    }

    suspend fun delete(contact: Contact){
        contactDAO.delete(contact)
    }

    fun getAll(): LiveData<List<Contact>> {
        return contactDAO.getAll()
    }

    fun getContact(contactid: Int): LiveData<Contact>{
        return contactDAO.getContact(contactid)
    }
}