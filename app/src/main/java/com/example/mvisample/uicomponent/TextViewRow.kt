package com.example.mvisample.uicomponent

import android.view.View
import com.example.mvisample.R
import com.example.mvisample.uicomponent.genericrecycler.RecyclerViewModel
import com.example.mvisample.uicomponent.genericrecycler.RecyclerModelViewHolder
import com.google.android.material.textview.MaterialTextView

class TextViewRow(
    val text: String
): RecyclerViewModel {

    override fun getLayout() = R.layout.text_row

    override fun onCreateViewHolder(view: View): RecyclerModelViewHolder<RecyclerViewModel> {
        return TextViewRowViewHolder(view)
    }
}

class TextViewRowViewHolder(private val view: View): RecyclerModelViewHolder<RecyclerViewModel>(view) {
    override fun bind(model: RecyclerViewModel) {
        model as TextViewRow
        view.findViewById<MaterialTextView>(R.id.text_view).text = model.text
    }
}