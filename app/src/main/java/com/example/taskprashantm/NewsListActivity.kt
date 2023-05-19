package com.example.prashantmtask


import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.prashantmtask.apicall.ApiInterface
import com.example.prashantmtask.models.MainResponseNYTimes
import com.example.prashantmtask.models.Result
import com.example.taskhararoo.apicall.ApiClient
import com.example.taskp.adapters.NewsListAdapter
import com.example.taskprashantm.R

import com.example.taskprashantm.databinding.ActivityNewsListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsListActivity : AppCompatActivity() {
    private lateinit var apiInterface: ApiInterface;
    private lateinit var binding: ActivityNewsListBinding
    private var mainLIst: ArrayList<Result> = ArrayList()
    private var filterList: ArrayList<Result> = ArrayList()
    lateinit var newsAdapter: NewsListAdapter;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news_list)
        init()
        setAdapter()
        apiCall()
    }

    /*Adapter + Recyclerview */
    fun setAdapter() {
        binding.rvList.layoutManager = LinearLayoutManager(this);
        newsAdapter = NewsListAdapter(filterList);
        binding.rvList.adapter = newsAdapter;
        binding.rvList.setHasFixedSize(false)
    }

    /*  Function to call API and parsing the data */
    private fun apiCall() {
        val callAPElementCall: Call<MainResponseNYTimes> =
            apiInterface.getAllfeeds("30", "jhP5oWeH4PeqdbypWAH9RJmqEzkSLyKb");

        binding.progressbar?.setVisibility(View.VISIBLE);
        callAPElementCall.enqueue(object : Callback<MainResponseNYTimes> {
            override fun onFailure(call: Call<MainResponseNYTimes>?, t: Throwable?) {
                Log.e("ERROR", t?.localizedMessage.toString())
                binding.progressbar.visibility = View.GONE;
            }

            override fun onResponse(
                call: Call<MainResponseNYTimes>?, response: Response<MainResponseNYTimes>?
            ) {
                binding.progressbar.visibility = View.GONE;

                val responseMain = response?.body()
                if (responseMain != null) {
                    mainLIst.clear()
                    mainLIst.addAll(responseMain.results)
                    filterList.clear()
                    filterList.addAll(responseMain.results)
                    newsAdapter.notifyDataSetChanged()
                }

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val search: MenuItem = menu.findItem(R.id.action_search)
        val searchView = search.getActionView() as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText.toString())
                return true;
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    /* To filter the main list and add into to filterd list */
    private fun filterList(string: String) {
        if (string!!.isNotEmpty()) {
            filterList.clear();
            for (item in mainLIst) {
                if (item.title.lowercase().contains(string!!.lowercase())) {
                    filterList.add(item)
                }
            }

        } else {
            filterList.clear();
            filterList.addAll(mainLIst);

        }
        newsAdapter.notifyDataSetChanged()
    }

    private fun init() {
        supportActionBar?.elevation = 5.0f;
        binding.progressbar.visibility = View.GONE;
        supportActionBar?.title = getString(R.string.ny_times_most_popular)
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu);
        apiInterface = ApiClient().getClient().create(ApiInterface::class.java)
    }
}