package com.example.halfway.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.example.halfway.listeners.OnFactClickListener
import com.example.halfway.model.Facts

class FactsViewHolder(
    private val view: View,
    private val requestManager: RequestManager,
    private val listener: OnFactClickListener,
    private val viewPreloadSizeProvider: ViewPreloadSizeProvider<String>
) : RecyclerView.ViewHolder(view), View.OnClickListener {

    lateinit var image: ImageView
    lateinit var title: TextView
    lateinit var desc: TextView

    override fun onClick(p0: View?) {
        listener.onFactClick(adapterPosition)
    }

    fun onBind(fact: Facts) {
        requestManager
            .load(fact.imageUrl)
            .into(image)

        title.setText(fact.title)
        desc.setText(fact.description)
        viewPreloadSizeProvider.setView(image)
    }
}