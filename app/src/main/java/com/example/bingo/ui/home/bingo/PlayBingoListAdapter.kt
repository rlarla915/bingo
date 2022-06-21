package com.example.bingo.ui.home.bingo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.bingo.MainActivity
import com.example.bingo.R
import com.example.bingo.databinding.*

class PlayBingoListAdapter(val mainActivity: MainActivity, val dataSet: ArrayList<String>) :
    RecyclerView.Adapter<PlayBingoListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(val mainActivity: MainActivity, val binding: ListItemPlayBingoBinding) : RecyclerView.ViewHolder(binding.root) {
        var clicked : Boolean = false
        fun bind(data:String, position: Int){
            binding.playBingoListItemText.text = data
            binding.playBingoListItemText.setOnClickListener {
                if (clicked) {
                    binding.playBingoListItemText.setBackgroundResource(R.drawable.radius_border)
                }
                else {
                    binding.playBingoListItemText.setBackgroundResource(R.drawable.radius_border_filled)
                }
                clicked = !clicked
                mainActivity.playBingoItem(position, clicked)
            }
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding = ListItemPlayBingoBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(mainActivity, binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(dataSet[position], position)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}