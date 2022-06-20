package com.example.bingo.ui.home.bingo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bingo.MainActivity
import com.example.bingo.databinding.FragmentCreateBingoBinding
import com.example.bingo.databinding.FragmentHomeBinding
import java.sql.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CreateBingoFragment : Fragment() {

    private var _binding: FragmentCreateBingoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var mainActivity: MainActivity

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCreateBingoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mainActivity = activity as MainActivity

        binding.createBingoTitle.setText(arguments?.getString("title"))


        val dataSet : ArrayList<Int> = arrayListOf()
        for (i in 0..24){
            dataSet.add(i)
        }
        binding.createBingoList.layoutManager = GridLayoutManager(mainActivity, 5)
        binding.createBingoList.adapter = CreateBingoListAdapter(mainActivity, dataSet)
        binding.createBingoSubmit.setOnClickListener {
            var check : Boolean = true
            for (str in mainActivity.createBingoItemArr){
                if (str == ""){
                    check = false
                    break
                }
            }
            if (check){
                val df: DateFormat = SimpleDateFormat("yyyy/MM/dd")
                val sendData = hashMapOf(
                    "title" to binding.createBingoTitle.text.toString(),
                    "writer" to "침착맨", // [fix] user 기반으로 다시
                    "date" to df.format(Date()),
                    "array" to mainActivity.createBingoItemArr
                )
                mainActivity.db.collection("bingo").add(sendData).addOnSuccessListener {
                    Toast.makeText(mainActivity, "작성되었습니다", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(mainActivity, "등록에 실패했습니다", Toast.LENGTH_SHORT).show()
                }
            }
            else {
                Toast.makeText(mainActivity, "비어있는 칸이 있습니다", Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}