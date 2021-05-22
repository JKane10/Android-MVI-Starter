package com.example.mvisample.uicomponent

import android.view.View
import com.example.mvisample.R
import com.example.mvisample.uicomponent.genericrecycler.RecyclerViewModel
import com.example.mvisample.uicomponent.genericrecycler.RecyclerModelViewHolder
import com.google.android.material.textview.MaterialTextView

class DoubleTextViewRow(
    val leftText: String,
    val rightText: String
) : RecyclerViewModel {

    override fun getLayout() = R.layout.double_text_row

    override fun onCreateViewHolder(view: View): RecyclerModelViewHolder<RecyclerViewModel> {
        return DoubleTextViewRowViewHolder(view)
    }
}

class DoubleTextViewRowViewHolder(
    private val view: View
) : RecyclerModelViewHolder<RecyclerViewModel>(view) {
    override fun bind(viewModel: RecyclerViewModel) {
        viewModel as DoubleTextViewRow
        view.findViewById<MaterialTextView>(R.id.left_text).text = viewModel.leftText
        view.findViewById<MaterialTextView>(R.id.right_text).text = viewModel.rightText
    }
}