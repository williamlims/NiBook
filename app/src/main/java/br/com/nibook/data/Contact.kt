package br.com.nibook.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey(autoGenerate = true) val id:Int,
    var name: String,
    var surname: String,
    var birthday: String,
    var phone: String,
    var email: String
)
