package com.example.budgettracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_add_transaction.*

class AddTransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        labelInput.addTextChangedListener {
            if(it!!.count()>0){
                labellayout.error=null
            }
        }

        amountInput.addTextChangedListener {
            if(it!!.count()>0){
                amountlayout.error=null
            }
        }

        addtxnbtn.setOnClickListener {
            val label=labelInput.text.toString()
            val amount=amountInput.text.toString().toDoubleOrNull()
            if(label.isEmpty()){
                labellayout.error="Please enter valid label"
            }
            if(amount==null){
                amountlayout.error="Please enter a valid amount"
            }
        }

        closebtn.setOnClickListener{
            finish()
        }
    }
}