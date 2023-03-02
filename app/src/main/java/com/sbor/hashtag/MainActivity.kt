package com.sbor.hashtag

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.sbor.hashtag.adapter.ItemAdapter
import com.sbor.hashtag.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ItemAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter
    private var hashtags = ArrayList<String>()
    private var filterHashTags = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = ItemAdapter(this)
        setAdapter()
        initClickers()
    }

    private fun setAdapter() {
        binding.recyclerView.adapter = adapter
    }

    private fun initClickers() {
        with(binding) {
            btnSend.setOnClickListener {
                val text = enterYourName.text.toString()
                val splitText = text.split(" ")
                splitText.map {
                    if (it.startsWith("#")) {
                        hashtags.add(it)
                    }
                }
                enterYourName.setText("")
                binding.recyclerView.isVisible = false
            }

            enterYourName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?, start: Int, count: Int, after: Int
                ) {
                    // Не используется
                }

                override fun onTextChanged(
                    text: CharSequence?, start: Int, before: Int, count: Int
                ) {
                    if (!text.isNullOrEmpty()) {
                        if (text.contains("#")) {
                            val tex = text.toString()
                            val ts = tex.split(" ")
                            var hashText = ""
                            ts.map {
                                if (it.startsWith("#")) {
                                    hashText = it
                                }
                            }
                            filterHashTags = hashtags.filter {
                                it.contains(hashText) } as ArrayList<String>
                            adapter.setItems(filterHashTags)
                            binding.recyclerView.adapter = adapter
                        } else {
                            filterHashTags.clear()
                        }
                    } else {
                        filterHashTags.clear()
                    }
                    binding.recyclerView.isVisible = filterHashTags.isNotEmpty()
                }
                override fun afterTextChanged(s: Editable?) {
                    // Не используется
                }
            })
        }
    }

    override fun onClick(name: String, position: Int) {
        binding.enterYourName.append(name.replace("#", ""))
        binding.recyclerView.isVisible = false
    }
}