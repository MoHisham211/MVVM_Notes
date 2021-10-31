package mo.zain.mvvmnotes.fragment

import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.textfield.TextInputEditText
import mo.zain.mvvmnotes.R
import mo.zain.mvvmnotes.data.Note
import mo.zain.mvvmnotes.viewModel.NoteViewModel


class UpdateFragment : Fragment() {

    lateinit var title: TextInputEditText
    lateinit var desc: TextInputEditText
    lateinit var btnAd: Button
    private val args by navArgs<UpdateFragmentArgs>()
    lateinit var noteViewModel:NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_update, container, false)

        title=view.findViewById(R.id.update_title)
        desc=view.findViewById(R.id.update_description)
        btnAd=view.findViewById(R.id.updateNote)

        title.setText(args.customObject.title)
        desc.setText(args.customObject.description)
        noteViewModel=ViewModelProvider(this).get(NoteViewModel::class.java)

        btnAd.setOnClickListener {
            updateNotes()
        }
        setHasOptionsMenu(true)
        return view
    }

    private fun updateNotes() {
        val titleTxt=title.text.toString()
        val descTxt=desc.text.toString()
        if (inputCheck(titleTxt,descTxt)){
            var note= Note(args.customObject.id,titleTxt,descTxt)
            noteViewModel.updateNote(note)
            Toast.makeText(requireContext(), "Done", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }else{
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete,menu)
        //super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.delete->{
                deleteNote()
                findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        var note= args
        noteViewModel.deleteNote(note.customObject)
    }

    private fun inputCheck(title : String,Desc : String):Boolean{

        return !(TextUtils.isEmpty(title)&& TextUtils.isEmpty(Desc))
    }

}