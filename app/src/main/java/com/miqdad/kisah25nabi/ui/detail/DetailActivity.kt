package com.miqdad.kisah25nabi.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.miqdad.kisah25nabi.R
import com.miqdad.kisah25nabi.data.KisahResponse
import com.miqdad.kisah25nabi.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initView()
    }

    private fun initView() {
        val data = intent.getParcelableExtra<KisahResponse>("EXTRA_DATA")
        binding.apply {
            Glide.with(this@DetailActivity)
                .load(data?.imageUrl)
                .into(detailImage)
            if (data != null) {
                detailNama.text = data.name
                detailDesk.text = data.description
                detailTempat.text = data.tmp
                detailUsia.text = data.usia
            }

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}