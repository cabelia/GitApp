package com.example.submission2.view

import com.example.submission2.adapter.SectionsPagerAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import coil.load
import com.example.submission2.api.GithubDetail
import com.example.submission2.api.GithubService
import coil.transform.CircleCropTransformation
import com.example.submission2.R
import com.example.submission2.databinding.ActivityDetailUserBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.example.submission2.db.FavoriteRepos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding

    companion object {
        @StringRes
        private val TAB_TITLE = intArrayOf(
            R.string.tab_followers,
            R.string.tab_following,
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repos = FavoriteRepos(this)

        val username: String = intent.extras?.get("username") as String
        repos.findAll(username).observe(this, androidx.lifecycle.Observer<List<GithubDetail>> {
            binding.toggleButton.isChecked = it.isNotEmpty()
        })

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLE[position])
        }.attach()
        supportActionBar?.elevation = 0f

        val Username = intent.extras?.get("username")
        UsernameGithub.username = Username.toString()
        val service = GithubService.depRetrofit.getDetailGit(Username as String)

        service.enqueue(object : Callback<GithubDetail> {
            override fun onResponse(
                call: Call<GithubDetail>,
                response: Response<GithubDetail>
            ) {

                val dataResponse: GithubDetail = response.body()!!
                binding.imgAvatar.load(dataResponse.avatarUrl) {
                    transformations(CircleCropTransformation())
                }
                binding.tvName.text = dataResponse.name
                binding.tvFollower.text = dataResponse.followers.toString()
                binding.tvFollowing.text = dataResponse.following.toString()
                binding.tvRepository.text = dataResponse.publicRepos.toString()
                binding.tvUsername.text = dataResponse.login
                dataResponse.location?.let { binding.tvLocation.text = it }
                    ?: run { binding.tvLocation.text = "-" }
                dataResponse.company?.let { binding.tvCompany.text = it }
                    ?: run { binding.tvCompany.text = "-" }

                binding.toggleButton.setOnCheckedChangeListener { buttonView, _isChecked ->
                    if (_isChecked) {
                        GlobalScope.launch(Dispatchers.IO) { repos.addToFavorite(dataResponse) }
                    } else {
                        GlobalScope.launch(Dispatchers.IO) { repos.removeFavorite(dataResponse) }
                    }
                }
            }

            override fun onFailure(call: Call<GithubDetail>, t: Throwable) {
                Toast.makeText(this@DetailUserActivity, "Error", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    object UsernameGithub {
        var username: String? = null
    }
}