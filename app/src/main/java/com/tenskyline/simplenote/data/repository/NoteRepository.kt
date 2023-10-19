package com.tenskyline.simplenote.data.repository

import com.tenskyline.simplenote.data.database.dao.NoteDao
import com.tenskyline.simplenote.data.model.Note

class NoteRepository(private val noteDao: NoteDao) {
    suspend fun insertNote(note:Note){
        noteDao.insertNote(note)
    }
    suspend fun getNotes():List<Note> {
        return noteDao.getNotes()
    }
}