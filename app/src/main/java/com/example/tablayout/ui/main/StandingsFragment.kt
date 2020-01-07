package com.example.tablayout.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer

import com.example.tablayout.R

class StandingsFragment : Fragment() {

    private lateinit var standingsViewModel: StandingsViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        standingsViewModel=ViewModelProviders.of(this).get(StandingsViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER)?:1)
        }
    }


    private lateinit var viewModel: StandingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.standings_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.section_label)
        standingsViewModel.text.observe(this, Observer<String> {
            textView.text = it
        })
        return root    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StandingsViewModel::class.java)
        // TODO: Use the ViewModel
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): StandingsFragment {
            return StandingsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

}
