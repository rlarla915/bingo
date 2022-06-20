package com.example.bingo.ui.home.bingo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.bingo.MainActivity
import com.example.bingo.R
import com.example.bingo.databinding.ActivityMainBinding
import com.example.bingo.databinding.BingoListItemBinding
import com.example.bingo.databinding.DialogCreateListItemBinding
import com.example.bingo.databinding.ListItemCreateBingoBinding

class CreateBingoListAdapter(val mainActivity: MainActivity, val dataSet: ArrayList<Int>) :
    RecyclerView.Adapter<CreateBingoListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(val mainActivity: MainActivity, val binding: ListItemCreateBingoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:Int){
            binding.createBingoListItemText.setOnClickListener {
                val dialogCreateBingoBinding: DialogCreateListItemBinding = DialogCreateListItemBinding.inflate(mainActivity.layoutInflater)
                val dialog = AlertDialog.Builder(mainActivity)
                    .setPositiveButton("입력") {dialog, id ->
                        val tmpStr : String = dialogCreateBingoBinding.createBingoListItemContext.text.toString()
                        if (tmpStr == "") {
                            binding.createBingoListItemText.text = "눌러서 입력해주세요"
                        }
                        else {
                            binding.createBingoListItemText.text = tmpStr
                        }
                        // 이걸 adapter와 fragment의 interface implement로 구현할 수 도 있다.
                        mainActivity.saveBingoItem(data, tmpStr)

                    }
                    .setNegativeButton("취소") {dialog, id ->

                    }
                dialog.setView(dialogCreateBingoBinding.root)
                dialog.show()
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding = ListItemCreateBingoBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(mainActivity, binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(dataSet[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}