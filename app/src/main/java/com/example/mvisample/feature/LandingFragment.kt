package com.example.mvisample.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvisample.Application
import com.example.mvisample.MainActivity
import com.example.mvisample.R
import com.example.mvisample.uicomponent.DoubleTextViewRow
import com.example.mvisample.uicomponent.genericrecycler.RecyclerViewModel
import com.example.mvisample.uicomponent.TextViewRow
import com.example.mvisample.uicomponent.genericrecycler.RecyclerModelAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect

class LandingFragment : Fragment() {

    companion object {
        const val TAG: String = "LandingFragment"
        fun newInstance() = LandingFragment()
    }

    private val viewModel by viewModels<LandingViewModel>()
    private lateinit var recyclerView: RecyclerView
    private val adapter = RecyclerModelAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.landing_fragment, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        viewModel.sampleRepo = (activity?.application as Application).sampleRepo

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                render(it)
            }
        }

        viewModel.dataLoaded.observe(viewLifecycleOwner, Observer { dataLoaded ->
            if (dataLoaded) Snackbar.make(view, "Data Loaded", Snackbar.LENGTH_LONG).show()
        })

        view.findViewById<Button>(R.id.button_view).setOnClickListener {
            viewModel.dispatch(LandingViewAction.ButtonClicked)
        }

        view.findViewById<Button>(R.id.button_load).setOnClickListener {
            viewModel.dispatch(LandingAction.LoadInitialData)
        }

        return view
    }

    private fun render(state: LandingState) {
        (activity as? MainActivity)?.showSpinner(state.isLoading)
        val models = mutableListOf<RecyclerViewModel>()
        models.add(TextViewRow("Count: ${state.count}"))
        models.add(DoubleTextViewRow("LEFT TEXT", "RIGHT TEXT"))

        for (i in 0 until state.posts.count()) {
            models.add(TextViewRow(state.posts[i].body))
        }

        adapter.viewModels = models
        adapter.notifyDataSetChanged()
    }
}