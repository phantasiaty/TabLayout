package com.example.tablayout.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.tablayout.LoginActivity

import com.example.tablayout.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.profile_fragment.*
import org.w3c.dom.Text


class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel

    private var mDatabaseReference: DatabaseReference? = null
    private var mDatabase: FirebaseDatabase? = null
    private var mAuth: FirebaseAuth? = null

    private var tvUsername: TextView? = null
    private var tvEmail: TextView? = null
    private var tvEmailVerified: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        profileViewModel=ViewModelProviders.of(this).get(ProfileViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER)?:1)

        }
    }



    private lateinit var viewModel: ProfileViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.profile_fragment, container, false)
//        val textView: TextView = root.findViewById(R.id.section_label)
//
//        profileViewModel.text.observe(this, Observer<String> {
//            textView.text = it
//        })
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")
        mAuth = FirebaseAuth.getInstance()

        tvUsername = root.findViewById(R.id.tv_username) as TextView
        tvEmail = root.findViewById(R.id.tv_email) as TextView
        tvEmailVerified = root.findViewById(R.id.tv_email_verified) as TextView
        return root    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }


    override fun onStart() {
        super.onStart()
        val mUser = mAuth!!.currentUser
        val mUserReference = mDatabaseReference!!.child(mUser!!.uid)
        tvEmail!!.text = mUser.email
        tvEmailVerified!!.text = mUser.isEmailVerified.toString()
        mUserReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tvUsername!!.text = snapshot.child("name").value as String
            }
            override fun onCancelled(databaseError: DatabaseError) {}
        })
        signOut!!.setOnClickListener {

            mAuth!!.signOut()
            startActivity(Intent(activity,LoginActivity::class.java))
            Toast.makeText(activity,"Sign Out Successfully",Toast.LENGTH_SHORT).show()
        }
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
        fun newInstance(sectionNumber: Int): ProfileFragment {
            return ProfileFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }



}
