package com.example.learningkotlin

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {


    var itemList = mutableListOf<Item>(
        Item("Pintar la casa", false),
        Item("Recortar la grama", false),
        Item("Lavar la guagua", false),
        Item("Ir al correo", false),
        Item("Ir de compras", false),
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addNewItemButton = findViewById<Button>(R.id.addNewItemButton)
        val newItemEditText = findViewById<EditText>(R.id.newItemEditText)

        val adapter = ItemAdapter(itemList)
        val recyclerView = findViewById<RecyclerView>(R.id.itemRecylerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addNewItemButton.setOnClickListener {
            if (newItemEditText.text.toString() != "") {
                itemList.add(Item(newItemEditText.text.toString(), false))
                adapter.notifyItemInserted(itemList.size -1)
                newItemEditText.setText("")
            } else {
                showAlertMesssage("ERROR", "Enter an item name...")
            }
        }

    }


    fun showAlertMesssage(title: String, message: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setCancelable(true)
        alertDialogBuilder.setTitle(title)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("Ok", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.cancel()
        })

        val alert = alertDialogBuilder.create()
        alert.show()
    }
}