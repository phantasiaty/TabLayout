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


class WorkoutFragment : Fragment() {

    private lateinit var workoutViewModel: WorkoutViewModel

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        workoutViewModel=ViewModelProviders.of(this).get(WorkoutViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER)?:1)
        }
    }


    private lateinit var viewModel: WorkoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.workout_fragment, container, false)
        val textView: TextView = root.findViewById(R.id.section_label)

        workoutViewModel.text.observe(this, Observer<String> {
            textView.text = it

        })
        return root    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WorkoutViewModel::class.java)
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
        fun newInstance(sectionNumber: Int): WorkoutFragment {
            return WorkoutFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

}






