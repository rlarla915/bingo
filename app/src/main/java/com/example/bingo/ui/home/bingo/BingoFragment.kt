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

class BingoFragment : Fragment() {

    private var _binding: FragmentBingoBinding? = null

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
                binding.playBingoList.adapter = PlayBingoListAdapter(mainActivity, dataSet)
            }
            .addOnFailureListener {

            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}