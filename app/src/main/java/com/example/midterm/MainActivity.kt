package com.example.midterm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.midterm.data.repository.LocalDatabase
import com.example.midterm.data.repository.RetrofitDatabase
import com.example.midterm.data.viewmodel.DataViewModel
import com.example.midterm.database.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val itemViewModel by lazy {
        ViewModelProviders.of(this,
            DataViewModel.Factory(
                RetrofitDatabase(),
                LocalDatabase(
                    AppDatabase.getInstance(this)?.getItemDao()
                )

            ))[DataViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        test()
    }

    private fun test() {
        itemViewModel.itemsLiveDataRetrofit.observe(this, Observer {items->
            println("___Aa" + items)
            whereToShow.text = items.all?.get(0).toString()
        })

        itemViewModel.itemsLiveDataDao.observe(this, Observer { items ->
            println("___" + items)
            whereToShowDaoData.text = items.all?.get(0).toString()
        })

        itemViewModel.getCatsFacts()
    }
}
