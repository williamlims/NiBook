package br.com.nibook.repository

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

    suspend fun getAll(){
        contactDAO.getAll()
    }

    suspend fun getContact(contactid: Int){
        contactDAO.getContact(contactid)
    }
}