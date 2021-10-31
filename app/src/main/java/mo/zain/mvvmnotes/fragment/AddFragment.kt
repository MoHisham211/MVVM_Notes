package mo.zain.mvvmnotes.fragment

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.google.android.material.textfield.TextInputEditText
import mo.zain.mvvmnotes.R
import mo.zain.mvvmnotes.data.Note
import mo.zain.mvvmnotes.viewModel.NoteViewModel


class AddFragment : Fragment() {
    lateinit var noteViewModel: NoteViewModel
    lateinit var title: TextInputEditText
    lateinit var desc: TextInputEditText
    lateinit var btnAd:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_add, container, false)

        noteViewModel=ViewModelProvider(this).get(NoteViewModel::class.java)
        title=view.findViewById(R.id.title)
        desc=view.findViewById(R.id.description)
        btnAd=view.findViewById(R.id.addNote)

        btnAd.setOnClickListener {
            addNotesToDB()
        }
        return view
    }

    private fun addNotesToDB() {
        val titleTxt=title.text.toString()
        val descTxt=desc.text.toString()
        if (inputCheck(titleTxt,descTxt)){
            var note=Note(0,titleTxt,descTxt)
            noteViewModel.addNote(note)
            Toast.makeText(requireContext(), "Done", Toast.LENGTH_LONG).show()
            title.setText("")
            desc.setText("")


        }else{
            Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()

        }
    }
    private fun inputCheck(title : String,Desc : String):Boolean{

        return !(TextUtils.isEmpty(title)&&TextUtils.isEmpty(Desc))
    }
}