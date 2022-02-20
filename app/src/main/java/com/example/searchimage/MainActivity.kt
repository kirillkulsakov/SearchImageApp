package com.example.searchimage


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchimage.data.UnsplashData
import com.example.searchimage.databinding.ActivityMainBinding
import com.example.searchimage.ui.ConnectionLiveData
import com.example.searchimage.ui.MainActivityViewModel
import com.example.searchimage.ui.PhotoAdapter

class   MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var cld: ConnectionLiveData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkNetworkConnection()
        initRecyclerView()

        createData("cat")
    }


    private fun checkNetworkConnection() {
        cld = ConnectionLiveData(application)

        cld.observe(this) { isConnected ->
            if (!isConnected) {
                binding.tvEthernetError.visibility = View.VISIBLE
            }
            else{
                binding.tvEthernetError.visibility = View.INVISIBLE
            }
        }
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            photoAdapter = PhotoAdapter(context)
            binding.recyclerView.adapter = photoAdapter

        }
    }

    private fun createData(input: String) {

        val viewModel = ViewModelProvider(this)?.get(MainActivityViewModel::class.java)
        viewModel.getPhotoListDataObserver().observe(this, Observer<UnsplashData> {

            photoAdapter.setListData(it.result)
            photoAdapter.notifyDataSetChanged()

        })
        supportActionBar?.title = input.capitalize()
        viewModel.makeApiCall(input)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)

        if (searchItem != null){

            val searchView  = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        createData(query.lowercase())
                    }
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    return true
                }
            })
        }

        return super.onCreateOptionsMenu(menu)
    }

}

