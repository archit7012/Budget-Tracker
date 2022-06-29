package com.example.budgettracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var transactions:ArrayList<Transaction>

    private lateinit var transactionAdapter: TransactionAdapter

    private lateinit var linearlayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transactions= arrayListOf(
            Transaction("Weekend Budget", 600.00),
            Transaction("Breakfast", 200.00),
            Transaction("Lunch", 800.00),
            Transaction("Dinner", -1600.00),
            Transaction("Weekend Budget", -900.00)
        )

        transactionAdapter= TransactionAdapter(transactions)

        linearlayoutManager= LinearLayoutManager(this)

        recyclerview.apply {
            adapter=transactionAdapter
            layoutManager=linearlayoutManager
        }
        updatedashboard()

        addbtn.setOnClickListener{
            val intent=Intent(this, AddTransactionActivity::class.java)
            startActivity(intent)
        }

    }

    private fun updatedashboard(){
        val totalamount=transactions.map { it.amount }.sum()
        val budgetamount=transactions.filter { it.amount>0 }.map { it.amount }.sum()
        val expenseamount=totalamount-budgetamount

        balance.text="₹ %.2f".format(totalamount)
        budget1.text="₹ %.2f".format(budgetamount)
        expense1.text="₹ %.2f".format(expenseamount)
    }

}