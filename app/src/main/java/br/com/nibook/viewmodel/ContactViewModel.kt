package br.com.nibook.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import br.com.nibook.data.Contact
import br.com.nibook.data.ContactDatabase
import br.com.nibook.repository.ContactRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContactViewModel(application: Application): AndroidViewModel(application){
    private val repository: ContactRepository
    lateinit var contact : LiveData<Contact>

    init { val dao = ContactDatabase.getDatabase(application).contactDAO()
        repository = ContactRepository(dao)
    }

    fun insert(contact: Contact) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(contact)
    }
}