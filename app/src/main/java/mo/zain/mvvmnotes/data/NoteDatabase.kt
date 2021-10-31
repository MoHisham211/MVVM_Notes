package mo.zain.mvvmnotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1,entities = [Note::class])

abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao():NoteDao

    companion object{
        @Volatile
        private var INSTANCE:NoteDatabase?=null

        fun getInstance(context: Context):NoteDatabase{
            val tempInstance= INSTANCE
            if (tempInstance!=null){
                return tempInstance
            }

            synchronized(this){
                val instance=Room.databaseBuilder(
                    context.applicationContext
                ,NoteDatabase::class.java,"DB").build()


                INSTANCE=instance
                return instance
            }

        }
    }

}