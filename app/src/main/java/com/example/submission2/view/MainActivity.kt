package com.example.submission2.view

import com.example.submission2.adapter.ListGithubAdapter
import com.example.submission2.alarm.SettingsActivity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2.R
import com.example.submission2.api.GithubResp
import com.example.submission2.api.GithubService
import com.example.submission2.api.Item
import com.example.submission2.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.toolbar.title = "$title"
        binding.toolbar.setNavigationOnClickListener {
            Toast.makeText(applicationContext, "navigation was click", Toast.LENGTH_SHORT)
                .show()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.progressBar.visibility = View.INVISIBLE
        binding.svSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.progressBar.visibility = View.VISIBLE
                val retrofit = GithubService.depRetrofit.getGit(query.toString())
                retrofit.enqueue(object : Callback<GithubResp> {
                    override fun onFailure(call: Call<GithubResp>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<GithubResp>,
                        response: Response<GithubResp>
                    ) {

                        val dataResponse: GithubResp = response.body()!!
                        val newList = dataResponse.items
                        val rvAdapter = ListGithubAdapter(newList)
                        binding.recyclerView.adapter = rvAdapter

                        rvAdapter.setOnItemClickCallback(object :
                            ListGithubAdapter.OnItemClickCallback {
                            override fun onItemClicked(data: Item) {
                                val move = Intent(this@MainActivity, DetailUserActivity::class.java)
                                move.putExtra("username", data.login)
                                startActivity(move)
                            }
                        })
                        binding.progressBar.visibility = View.GONE
                    }
                })
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.Settings) {
            val mIntent = Intent(this, SettingsActivity::class.java)
            startActivity(mIntent)
        }
        if (item.itemId == R.id.favoriteUser) {
            val mIntent = Intent(this, FavoriteActivity::class.java)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}
