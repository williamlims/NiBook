package br.com.nibook.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactDAO {
    @Insert
    suspend fun insert(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contact ORDER BY name")
    fun getAll(): LiveData<List<Contact>>

    @Query("SELECT * FROM contact WHERE id=:contactid")
    fun getContact(contactid:Int): LiveData<Contact>

}