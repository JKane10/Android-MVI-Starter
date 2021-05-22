package com.example.mvisample.uicomponent.genericrecycler

import android.view.View

/**
 * Interface to represent a generic recycler object view model. This allows for the recyclerview to
 * be populated with a list of generic view models representing various view holders to allow for a
 * declarative UI pattern.
 */
interface RecyclerViewModel {

    fun getLayout(): Int

    fun onCreateViewHolder(view: View): RecyclerModelViewHolder<RecyclerViewModel>
}