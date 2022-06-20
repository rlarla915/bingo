package com.example.bingo.ui.home.bingo

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.bingo.databinding.DialogCreateBingoBinding


class CreateBingoDialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Create Bingo")
                .setPositiveButton("만들기",
                    DialogInterface.OnClickListener { dialog, id ->
                        val dialogCreateBingoBinding: DialogCreateBingoBinding = DialogCreateBingoBinding.inflate(layoutInflater)

                        // START THE GAME!
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}