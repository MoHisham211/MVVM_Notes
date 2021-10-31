package mo.zain.mvvmnotes.adapter

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import mo.zain.mvvmnotes.R
import mo.zain.mvvmnotes.data.Note
import mo.zain.mvvmnotes.fragment.ListFragment
import mo.zain.mvvmnotes.fragment.ListFragmentDirections

class NoteAdapter(val notes:List<Note>) : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {
    val note:List<Note>

    init {
        this.note=notes
    }
    class ViewHolder(itemView: View,notes: List<Note>) : RecyclerView.ViewHolder(itemView) {
        lateinit var id:TextView
        lateinit var title:TextView
        init {
            id=itemView.findViewById(R.id.id)
            title=itemView.findViewById(R.id.title)
            itemView.setOnClickListener {
                val action= ListFragmentDirections.actionListFragmentToUpdateFragment(notes.get(adapterPosition))
                itemView.findNavController().navigate(action)
                //Toast.makeText(itemView.context,""+notes.get(adapterPosition).title,Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false),notes)
    }

    override fun onBindViewHolder(holder: NoteAdapter.ViewHolder, position: Int) {
        val note =note.get(position)
        holder.id.text=note.id.toString()
        holder.title.text=note.title
    }

    override fun getItemCount(): Int {
        return note.size
    }
}