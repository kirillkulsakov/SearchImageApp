package com.example.searchimage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchimage.api.RetroInstance
import com.example.searchimage.api.RetroService
import com.example.searchimage.data.UnsplashData
import com.example.searchimage.databinding.ActivityMainBinding
import com.example.searchimage.ui.PhotoAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var input: String = ""
    val retroInstance = RetroInstance

    //var recyclerView: RecyclerView = findViewById(R.id.recycler_view)
    lateinit var photoAdapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        createData()
    }

    private fun initRecyclerView() {
        binding.recyclerView?.apply {
            binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            photoAdapter = PhotoAdapter()
            binding.recyclerView.adapter = photoAdapter

        }
    }

    fun createData() {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFormAPI("apple")
        call.enqueue(object : Callback<List<UnsplashData>>{
            override fun onResponse(call: Call<List<UnsplashData>>, response: Response<List<UnsplashData>>) {
                if (response.isSuccessful) {
                    photoAdapter.setListData(response.body()?.first()!!.result)
                    Log.d("Jopa", "${response.body()?.first()!!.result}")
                    photoAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<UnsplashData>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error, ${t.message}", Toast.LENGTH_LONG).show()
            }
        })


    }
}

// })/*
//
// /*val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
// viewModel.getPhotoListDataObserver().observe(this, Observer<ResultList> {
//
// photoAdapter.setListData(it.result)
// photoAdapter.notifyDataSetChanged()
//
// })
// viewModel.makeApiCall("cat")*/
// }
//
// /*    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
// menuInflater.inflate(R.menu.menu, menu)
//
// val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
// val searchItem = menu?.findItem(R.id.action_search)
// val searchView = searchItem?.actionView as SearchView
//
// searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
//
//
// searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
// override fun onQueryTextSubmit(query: String?): Boolean {
// searchView.clearFocus()
// searchView.setQuery("", false)
// searchView.onActionViewCollapsed()
// return true
// }
//
// override fun onQueryTextChange(newText: String?): Boolean {
// return false
// }
// })
// return true
// }*/
// }