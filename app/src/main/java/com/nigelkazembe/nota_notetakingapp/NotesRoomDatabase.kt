package com.nigelkazembe.nota_notetakingapp

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import androidx.room.TypeConverters

import com.nigelkazembe.nota_notetakingapp.Notes
import com.nigelkazembe.nota_notetakingapp.NotesDao

@Database(entities = [(Notes::class), (Notess::class)], version = 1)
@TypeConverters(Converters::class)
abstract class NotesRoomDatabase:  RoomDatabase(){
    abstract fun noteDao(): NotesDao

    companion object{
        private var INSTANCE: NotesRoomDatabase? = null

        internal fun getDatabase(context: Context): NotesRoomDatabase? {//This whole function is to ensure that we only create one instance of the room database since we only need to create one
            if(INSTANCE == null) {
                synchronized(NotesRoomDatabase::class.java) {
                    if(INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder<NotesRoomDatabase> (
                            context.applicationContext,
                            NotesRoomDatabase::class.java, "notes_database").build()
                    }
                }
            }
            return INSTANCE
        }
    }
}