package com.example.bingo.ui.home.bingo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bingo.MainActivity
import com.example.bingo.R
import com.example.bingo.databinding.ActivityMainBinding
import com.example.bingo.databinding.BingoListItemBinding

class BingoListAdapter(val mainActivity: MainActivity, private val dataSet: ArrayList<List<String>>) :
    RecyclerView.Adapter<BingoListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(val mainActivity: MainActivity, private val binding: BingoListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:List<String>){
            binding.bingoListItemTitle.text = data[0]
            binding.bingoListItemWriter.text = data[1]
            binding.bingoListItemDate.text = data[2]
            binding.root.setOnClickListener {
                val bingoFragment = BingoFragment()
                mainActivity.supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_activity_main, bingoFragment.apply { arguments = Bundle().apply { putString("title", data[0]); putString("writer", data[1]); putString("date", data[2]); putString("id", data[3]) } }).commit()
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding = BingoListItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

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