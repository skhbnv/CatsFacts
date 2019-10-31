package com.example.midterm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm.adapters.CatsAdapter
import com.example.midterm.data.repository.LocalDatabase
import com.example.midterm.data.repository.RetrofitDatabase
import com.example.midterm.data.response.DefaultResponse
import com.example.midterm.data.viewmodel.DataViewModel
import com.example.midterm.database.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: CatsAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val itemViewModel by lazy {
        ViewModelProviders.of(this,
            DataViewModel.Factory(
                RetrofitDatabase(),
                LocalDatabase(
                    AppDatabase.getInstance(this)!!.getItemDao()
                )
            ))[DataViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeTheData()
        itemViewModel.getDaoCatsFacts()
    }


    private fun observeTheData() {
        itemViewModel.itemsLiveDataRetrofit.observe(this, Observer {items->
            println("___Aa" + items)
        })

        itemViewModel.itemsLiveDataDao.observe(this, Observer { items ->
            setRecyclerView(items.all)
        })
    }

    private fun setRecyclerView(items : List<DefaultResponse>) {
        println("___" + "setRecycler")
        viewManager = LinearLayoutManager(this)
        viewAdapter = CatsAdapter(items)

        recyclerView = findViewById<RecyclerView>(R.id.mainRecycler).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
