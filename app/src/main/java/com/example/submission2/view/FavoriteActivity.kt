package com.example.submission2.view

import com.example.submission2.adapter.ListUserAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission2.api.GithubDetail
import com.example.submission2.databinding.ActivityFavoriteBinding
import com.example.submission2.db.FavoriteRepos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.rvFavorite.layoutManager = LinearLayoutManager(this)

        val favoriteViewActivity = FavoriteRepos(this)
        binding.progressBar.visibility = View.VISIBLE
        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            GlobalScope.launch(Dispatchers.Main) {
            }
            favoriteViewActivity.getFavoriteUser().observe(this@FavoriteActivity, Observer {

                lifecycleScope.launchWhenCreated {
                    adapter = ListUserAdapter(it)
                    (adapter as ListUserAdapter).setOnItemClickCallback(object :
                        ListUserAdapter.OnItemClickCallback {
                        override fun onItemClicked(data: GithubDetail) {
                            val intent =
                                Intent(this@FavoriteActivity, DetailUserActivity::class.java)
                            intent.putExtra("username", data.login)
                            startActivity(intent)
                        }
                    })
                    binding.progressBar.visibility = View.GONE
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        val favoriteViewActivity = FavoriteRepos(this)
        binding.rvFavorite.apply {
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
            GlobalScope.launch(Dispatchers.Main) {

                favoriteViewActivity.getFavoriteUser().observe(this@FavoriteActivity, Observer {

                    lifecycleScope.launchWhenCreated {
                        adapter = ListUserAdapter(it)
                        (adapter as ListUserAdapter).setOnItemClickCallback(object :
                            ListUserAdapter.OnItemClickCallback {
                            override fun onItemClicked(data: GithubDetail) {
                                val intent =
                                    Intent(this@FavoriteActivity, DetailUserActivity::class.java)
                                intent.putExtra("username", data.login)
                                startActivity(intent)
                            }
                        })
                    }
                })

            }
        }
    }
}
