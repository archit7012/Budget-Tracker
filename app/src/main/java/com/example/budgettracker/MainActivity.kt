package com.example.budgettracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var transactions:List<Transaction>

    private lateinit var transactionAdapter: TransactionAdapter

    private lateinit var linearlayoutManager: LinearLayoutManager

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transactions= arrayListOf()

        transactionAdapter= TransactionAdapter(transactions)

        linearlayoutManager= LinearLayoutManager(this)

        db= Room.databaseBuilder(this, AppDatabase::class.java, "transactions").build()

        recyclerview.apply {
            adapter = transactionAdapter
            layoutManager = linearlayoutManager
        }

        addbtn.setOnClickListener{
            val intent=Intent(this, AddTransactionActivity::class.java)
            startActivity(intent)
        }

    }

    private fun fetchAll(){
        GlobalScope.launch {
            transactions=db.transactionDao().getAll()
            runOnUiThread{
                updatedashboard()
                transactionAdapter.setData(transactions)
            }
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

    override fun onResume() {
        super.onResume()
        fetchAll()
    }

}