package com.example.mvisample.uicomponent.genericrecycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Generic ViewHolder class to facilitate the binding of generic RecyclerModel classes.
 */
abstract class RecyclerModelViewHolder<RecyclerModel>(
    view: View
): RecyclerView.ViewHolder(view) {

     abstract fun bind(model: RecyclerModel)
}
