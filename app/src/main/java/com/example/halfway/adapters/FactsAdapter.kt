package com.example.halfway.adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.RequestManager
import com.bumptech.glide.util.ViewPreloadSizeProvider
import com.example.halfway.R
import com.example.halfway.listeners.OnFactClickListener
import com.example.halfway.model.Facts
import java.util.*

class FactsAdapter(
    private val listener: OnFactClickListener,
    private val requestManager: RequestManager,
    private val preloadSizeProvider: ViewPreloadSizeProvider<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), ListPreloader.PreloadModelProvider<String> {


    private var factList: List<Facts?>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        view = LayoutInflater.from(parent.context).inflate(R.layout.fact_item_view, parent, false)
        return FactsViewHolder(view, requestManager, listener, preloadSizeProvider)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as FactsViewHolder).onBind(factList?.get(position))
    }

    override fun getItemCount(): Int {
        return factList?.size ?: 0
    }

    override fun getPreloadItems(position: Int): MutableList<String> {
        val url: String? = factList?.get(position)?.imageUrl
        if (TextUtils.isEmpty(url)) {
            return Collections.emptyList()
        }
        return Collections.singletonList(url)
    }

    override fun getPreloadRequestBuilder(item: String): RequestBuilder<*>? {
        return requestManager.load(item)
    }

    fun getSelectedRecipe(position: Int): Facts? {
        if (factList != null) {
            if (factList?.size ?: 0 > 0) {
                return factList?.get(position)
            }
        }
        return null
    }

    fun setFacts(recipes: List<Facts?>) {
        factList = recipes
        notifyDataSetChanged()
    }
}