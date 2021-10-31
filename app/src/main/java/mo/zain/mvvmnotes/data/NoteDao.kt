package mo.zain.mvvmnotes.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {
    //Insert
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(note: Note)

    //Read Data
    @Query("SELECT * FROM note_table ORDER BY id ASC")
    fun read():LiveData<List<Note>>

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAll()



}