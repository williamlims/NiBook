package br.com.nibook.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[Contact::class], version=1)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun contactDAO(): ContactDAO

    companion object {
        @Volatile
        private var INSTANCE: ContactDatabase? = null

        fun getDatabase(context: Context): ContactDatabase {
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ContactDatabase::class.java,
                    "database.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}