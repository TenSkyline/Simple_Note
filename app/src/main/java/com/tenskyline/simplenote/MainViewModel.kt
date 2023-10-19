package com.tenskyline.simplenote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.tenskyline.simplenote.data.model.Note
import com.tenskyline.simplenote.data.repository.NoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(private val noteRepository: NoteRepository):ViewModel() {
    val uiState get() = _uiState.asStateFlow()
    private val _uiState = MutableStateFlow(MainUiState())
    fun insertNote()= viewModelScope.launch {
        val note = Note(title = "Catatan", description = "Belajar Kotlin", createdAt = "")
    }
    fun getNotes()= viewModelScope.launch {
        val notes = noteRepository.getNotes()
        _uiState.update{ it.copy(notes = notes) }
    }
}

data class MainUiState(
    val notes: List<Note> = emptyList()
)

class MainViewModelFactory(private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}