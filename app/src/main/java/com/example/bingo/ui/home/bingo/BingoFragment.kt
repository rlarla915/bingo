package com.example.bingo.ui.home.bingo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bingo.MainActivity
import com.example.bingo.databinding.FragmentBingoBinding
import com.example.bingo.databinding.FragmentHomeBinding

class BingoFragment : Fragment(), BingoClickListener{

    private var _binding: FragmentBingoBinding? = null
    var playBingoItemArr : MutableList<Boolean> = MutableList<Boolean>(25){false}
    var numBingo : Int = 0

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBingoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mainActivity = activity as MainActivity
        binding.playBingoWriter.setText(arguments?.getString("writer"))
        binding.playBingoDate.setText(arguments?.getString("date"))
        mainActivity.setAppbarTitle(arguments?.getString("title"))

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity.db.collection("bingo").document(arguments?.getString("id").toString()).get()
            .addOnSuccessListener { document ->
                val dataSet : ArrayList<String> = document.get("array") as ArrayList<String>
                binding.playBingoList.layoutManager = GridLayoutManager(mainActivity, 5)
                binding.playBingoList.adapter = PlayBingoListAdapter(mainActivity, this, dataSet)
            }
            .addOnFailureListener {

            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onBingoClick(pos: Int, clicked: Boolean) {
        playBingoItemArr[pos] = clicked
        checkBingo()
    }
    private fun checkBingo(){
        numBingo = 0
        for (i in 0..4){
            if (playBingoItemArr[i] && playBingoItemArr[5+i] && playBingoItemArr[10+i] && playBingoItemArr[15+i] && playBingoItemArr[20+i]){
                numBingo++
            }
            if (playBingoItemArr[5*i] && playBingoItemArr[5*i+1] && playBingoItemArr[5*i+2] && playBingoItemArr[5*i+3] && playBingoItemArr[5*i+4]){
                numBingo++
            }
        }
        if (playBingoItemArr[0] && playBingoItemArr[6] && playBingoItemArr[12] && playBingoItemArr[18] && playBingoItemArr[24]){
            numBingo++
        }
        if (playBingoItemArr[4] && playBingoItemArr[8] && playBingoItemArr[12] && playBingoItemArr[16] && playBingoItemArr[20]){
            numBingo++
        }
        binding.textNumBingo.text = "빙고 개수 : "+numBingo.toString()
    }
}