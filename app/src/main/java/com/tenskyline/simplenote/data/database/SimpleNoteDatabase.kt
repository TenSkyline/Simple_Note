package com.tenskyline.simplenote.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tenskyline.simplenote.data.database.dao.NoteDao
import com.tenskyline.simplenote.data.model.Note
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Note::class], version = 1)
abstract class SimpleNoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: SimpleNoteDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): SimpleNoteDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SimpleNoteDatabase::class.java,
                    "word_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}