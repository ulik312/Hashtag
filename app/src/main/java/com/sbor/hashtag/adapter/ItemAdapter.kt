package com.sbor.hashtag.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sbor.hashtag.databinding.ItemTvNameBinding

class ItemAdapter(private val listener: Listener) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    private val list = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ItemTvNameBinding.inflate(LayoutInflater.from(parent.context), parent,
                false))

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(list[position],listener, position)

    }
    fun setItems(word: ArrayList<String>) {
        list.clear()
        list.addAll(word)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }
    inner class ItemViewHolder(private val binding: ItemTvNameBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(name: String, listener: Listener, position: Int) {
            binding.tvName.text = name
            itemView.setOnClickListener {
                listener.onClick(name, position)
            }

        }

    }
    interface Listener{
        fun onClick(name: String, position: Int)
    }
}