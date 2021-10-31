package mo.zain.mvvmnotes.repository

import androidx.lifecycle.LiveData
import mo.zain.mvvmnotes.data.Note
import mo.zain.mvvmnotes.data.NoteDao

class NoteRepository(private val noteDao: NoteDao) {

    val ReadAllData:LiveData<List<Note>> =noteDao.read();

    suspend fun addNotes(note: Note)
    {
        noteDao.add(note)
    }
    suspend fun updateNote(note: Note)
    {
        noteDao.update(note)
    }
    suspend fun deleteNote(note: Note)
    {
        noteDao.delete(note)
    }
    suspend fun deleteAll()
    {
        noteDao.deleteAll()
    }
}