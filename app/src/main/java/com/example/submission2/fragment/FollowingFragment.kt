package com.example.submission2.fragment

import com.example.submission2.adapter.ListGithubAdapter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.submission2.api.GithubService
import com.example.submission2.api.Item
import com.example.submission2.view.DetailUserActivity
import com.example.submission2.databinding.FragmentFollowingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingFragment : Fragment() {
    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding as FragmentFollowingBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        recyclerView = binding.rvFollowing
        recyclerView.layoutManager = LinearLayoutManager(context)

        binding.progressBar.visibility = View.VISIBLE
        GithubService.depRetrofit.getFollowing(DetailUserActivity.UsernameGithub.username.toString())
            .enqueue(object : Callback<List<Item>> {
                override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                    Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                    val list = response.body()
                    recyclerView.adapter = list?.let {
                        ListGithubAdapter(it).also {
                            it.setOnItemClickCallback(object :
                                ListGithubAdapter.OnItemClickCallback {
                                override fun onItemClicked(data: Item) {
                                    Log.i("Gihubuser", data.toString())
                                }
                            })
                        }
                    }
                    binding.progressBar.visibility = View.GONE
                }
            })

        return binding.root
    }
}