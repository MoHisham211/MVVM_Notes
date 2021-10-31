package mo.zain.mvvmnotes.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mo.zain.mvvmnotes.data.Note
import mo.zain.mvvmnotes.data.NoteDatabase
import mo.zain.mvvmnotes.repository.NoteRepository

class NoteViewModel(application: Application):AndroidViewModel(application) {

    val readAllData : LiveData<List<Note>>

    private val repository : NoteRepository

    init {
        val noteDao =NoteDatabase.getInstance(application).noteDao()
        readAllData=noteDao.read()
        repository= NoteRepository(noteDao)
    }
    fun addNote(note: Note){
        viewModelScope.launch (Dispatchers.IO){
            repository.addNotes(note)
        }
    }
    fun updateNote(note: Note)
    {
        viewModelScope.launch (Dispatchers.IO){
            repository.updateNote(note)
        }
    }
    fun deleteNote(note: Note)
    {
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteNote(note)
        }
    }
    fun deleteAll()
    {
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAll()
        }
    }
}