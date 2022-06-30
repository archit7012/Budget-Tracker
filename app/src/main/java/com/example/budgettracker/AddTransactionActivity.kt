package com.example.budgettracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_add_transaction.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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
            val description=descriptionInput.text.toString()
            val amount=amountInput.text.toString().toDoubleOrNull()
            if(label.isEmpty()){
                labellayout.error="Please enter valid label"
            }
            else if(amount==null){
                amountlayout.error="Please enter a valid amount"
            }
            else{
                val transaction=Transaction(0, label, amount, description)
                insert(transaction)
            }
        }

        closebtn.setOnClickListener{
            finish()
        }
    }

    private fun insert(transaction: Transaction){
        val db= Room.databaseBuilder(this, AppDatabase::class.java, "transactions").build()
        GlobalScope.launch {
            db.transactionDao().insertAll(transaction)
            finish()
        }
    }
}