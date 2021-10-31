package mo.zain.mvvmnotes.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.runInterruptible
import mo.zain.mvvmnotes.R
import mo.zain.mvvmnotes.adapter.NoteAdapter
import mo.zain.mvvmnotes.data.Note
import mo.zain.mvvmnotes.viewModel.NoteViewModel


class ListFragment : Fragment() {

    lateinit var noteViewModel: NoteViewModel
    lateinit var recyclerView: RecyclerView
    lateinit var floatingActionButton: FloatingActionButton
    lateinit var listNotes:List<Note>
    lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_list, container, false)

        listNotes=ArrayList<Note>()

        recyclerView=view.findViewById(R.id.RV)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager=LinearLayoutManager(requireContext())


        noteViewModel=ViewModelProvider(this).get(NoteViewModel::class.java)
        noteViewModel.readAllData.observe(viewLifecycleOwner, Observer { notes ->
            noteAdapter= NoteAdapter(notes)
            recyclerView.adapter=noteAdapter
        })

        floatingActionButton=view.findViewById(R.id.floatingActionButton)
        floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete,menu)
        //super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete->
            {
                Toast.makeText(requireContext(),"Done",Toast.LENGTH_SHORT).show()
                noteViewModel.deleteAll()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}