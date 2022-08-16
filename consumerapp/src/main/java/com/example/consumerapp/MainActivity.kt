package com.example.consumerapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.consumerapp.DatabaseContract.CONTENT_URI
import com.example.consumerapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        Log.d("can", "onCreate: $CONTENT_URI")

        GlobalScope.launch(Dispatchers.Main) {
            val userFavorite = async(Dispatchers.IO) {
                val cursor = contentResolver.query(
                    CONTENT_URI,
                    null,
                    null,
                    null,
                    null
                )
                GitMappingHelper.mapCursorToArrayList(cursor)
            }
            val gitFav = userFavorite.await()
            binding.recyclerView.adapter = ListUserAdapter(gitFav).also {
                it.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: GithubDetail) {
                        Toast.makeText(this@MainActivity, data.name, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}