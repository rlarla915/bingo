package com.example.bingo.ui.home.bingo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bingo.R
import com.example.bingo.databinding.ActivityMainBinding
import com.example.bingo.databinding.BingoListItemBinding

class BingoListAdapter(private val dataSet: ArrayList<List<String>>) :
    RecyclerView.Adapter<BingoListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(private val binding: BingoListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:List<String>){
            Log.e("x", "QQQQQQQQ")
            Log.e("x", data.toString())
            binding.bingoListItemTitle.text = data[0]
            binding.bingoListItemWriter.text = data[1]
            binding.bingoListItemDate.text = data[2]
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        Log.e("x", "WWWWWW")
        val binding = BingoListItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
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