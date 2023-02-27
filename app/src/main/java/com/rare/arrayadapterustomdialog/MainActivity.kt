package com.rare.arrayadapterustomdialog

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.rare.arrayadapterustomdialog.databinding.ActivityMainBinding
import com.rare.arrayadapterustomdialog.databinding.CustomdialogBinding

class MainActivity : AppCompatActivity() {
    var arrayList = ArrayList<String>()
    lateinit var arrayAdapter: ArrayAdapter<String>
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList)
        binding.lv.adapter = arrayAdapter
        binding.fabAdd.setOnClickListener {
            /* var Dialogview = LayoutInflater.from(this).inflate(R.layout.arrayadapterustomdialog, null)
            var myDialog = Dialog(this)
            myDialog.setContentView(Dialogview)
            myDialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            myDialog.show()
            var btnCancel = Dialogview.findViewById<Button>(R.id.cdbtnSave)
            btnCancel.setOnClickListener{
                    myDialog.dismiss()
            } */
            //with binding
            var dialogView = CustomdialogBinding.inflate(layoutInflater)
            var dialog = Dialog(this)
            dialog.setContentView(dialogView.root)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.show()
            dialogView.btnSave.setOnClickListener {
                if (dialogView.etName.text.toString().trim().isEmpty()) {
                    dialogView.etName.error = "Enter the Name"
                }else {

                    arrayList.add(dialogView.etName.text.toString())
                    arrayAdapter.notifyDataSetChanged()
                    dialog.dismiss()

                }

            }
        }
        binding.lv.setOnItemClickListener { adapterView, view, position, l ->
            var dialogView = CustomdialogBinding.inflate(layoutInflater)
            var dialog = Dialog(this)
            dialog.setContentView(dialogView.root)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.show()
            dialogView.etName.setText(arrayList[position])
           dialogView.btnSave.setOnClickListener {
                if (dialogView.etName.text.toString().trim().isEmpty()) {
                    dialogView.etName.error = "Enter the Name"
                }  else {
                    arrayList.set(position, dialogView.etName.text.toString())
                    arrayAdapter.notifyDataSetChanged()
                    dialog.dismiss()

                }


            }


        }
    }
}



