package com.example.taskp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.prashantmtask.models.Result
import com.example.taskprashantm.databinding.ListItemBinding


class NewsListAdapter(private val largeNewsList: List<Result>) :
    RecyclerView.Adapter<LargeNewsViewHolder>() {
    private lateinit var binding: ListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LargeNewsViewHolder {
        binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LargeNewsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: LargeNewsViewHolder, position: Int) {
        val largeNews = largeNewsList[position]
        holder.bind(largeNews)
    }

    override fun getItemCount(): Int = largeNewsList.size

}

class LargeNewsViewHolder(
    private val binding: ListItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(largeNews: Result) {
        binding.tvTitleLargeNews.text = largeNews.title
        binding.tvSubtittle.text = largeNews.byline
        binding.tvdate.text = largeNews.published_date
        if (largeNews.media.size > 0 && largeNews.media.get(0).media_metadata.size > 0)
            Glide
                .with(binding.root)
                .load(largeNews.media.get(0).media_metadata.get(0).url).circleCrop()
                .into(binding.SivMain);

    }
}