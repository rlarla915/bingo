package com.example.bingo.ui.home

import android.app.Dialog
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bingo.MainActivity
import com.example.bingo.R
import com.example.bingo.databinding.DialogCreateBingoBinding
import com.example.bingo.databinding.FragmentHomeBinding
import com.example.bingo.ui.home.bingo.BingoListAdapter
import com.example.bingo.ui.home.bingo.CreateBingoDialog
import com.example.bingo.ui.home.bingo.CreateBingoFragment

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var mainActivity: MainActivity

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        mainActivity = activity as MainActivity

        val root : View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataSet : ArrayList<List<String>> = arrayListOf()
        Log.e("X", "AAAA")
        mainActivity.db.collection("bingo")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.e("X", "BBBB")
                    Log.e("test", document.get("title").toString())
                    dataSet.add(listOf(document.get("title").toString(), document.get("writer").toString(), document.get("date").toString()))
                }
                binding.bingoList.layoutManager = LinearLayoutManager(mainActivity)
                binding.bingoList.adapter = BingoListAdapter(dataSet)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }

        binding.fabAddBingo.setOnClickListener {
            val dialogCreateBingoBinding: DialogCreateBingoBinding = DialogCreateBingoBinding.inflate(layoutInflater)
            val dialog = AlertDialog.Builder(mainActivity)
                .setPositiveButton("만들기") {dialog, id ->
                    val title = dialogCreateBingoBinding.createBingoTitle.text.toString()

                    val createBingoFragment = CreateBingoFragment()
                    mainActivity.supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, createBingoFragment.apply { arguments = Bundle().apply { putString("title", title) } }).commit()
                }
                .setNegativeButton("취소") {dialog, id ->

                }
            dialog.setView(dialogCreateBingoBinding.root)
            dialog.show()
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}