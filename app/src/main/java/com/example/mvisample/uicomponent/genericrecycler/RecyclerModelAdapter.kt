package com.example.mvisample.uicomponent.genericrecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * A recycler view adapter that facilitates generic recycler view models.
 * RecyclerViewModels are responsible for implementing their own onCreateViewHolder methods to
 * facilitate the usage of different layouts
 */
class RecyclerModelAdapter : RecyclerView.Adapter<RecyclerModelViewHolder<RecyclerViewModel>>() {
    var viewModels: List<RecyclerViewModel> = mutableListOf()
    var currentPosition: Int = -1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerModelViewHolder<RecyclerViewModel> {
        val model = viewModels[currentPosition]
        return model.onCreateViewHolder(
            LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerModelViewHolder<RecyclerViewModel>,
        position: Int
    ) {
        holder.bind(viewModels[position])
    }

    override fun getItemCount(): Int {
        return viewModels.size
    }

    override fun getItemViewType(position: Int): Int {
        currentPosition = position
        return viewModels[position].getLayout()
    }
}