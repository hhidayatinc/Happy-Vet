package com.example.happyvet.ui.activity.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.happyvet.data.remote.Users
import com.example.happyvet.databinding.FragmentHomeBinding
import com.example.happyvet.ui.adapter.HomeAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    var userList = ArrayList<Users>()
    private lateinit var adapter: HomeAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        val layoutManager = LinearLayoutManager(requireActivity())

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        getUsersList()

        homeViewModel.getUsers()

        homeViewModel.listUsers.observe(viewLifecycleOwner){
            userList.add(it)
            adapter = HomeAdapter(userList)
            binding.rvList.layoutManager = layoutManager
            binding.rvList.setHasFixedSize(true)
            binding.rvList.adapter = adapter
        }
//        binding.rvList.layoutManager= LinearLayoutManager(getActivity())



        return root
    }

//    fun getUsersList(){
//        val firebase : FirebaseUser = FirebaseAuth.getInstance().currentUser!!
//        val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
//
//        databaseReference.addValueEventListener(object: ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                userList.clear()
//
//                for (dataSnapShot: DataSnapshot in snapshot.children){
//                    val user = dataSnapShot.getValue(Users::class.java)
//                    if (user != null) {
//                        if(user.userId.equals(firebase.uid)){
//                            userList.add(user)
//                        }
//                    }
//                    adapter = HomeAdapter(userList)
//                    binding.rvList.adapter = adapter
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(getActivity(), error.message, Toast.LENGTH_SHORT).show()
//            }
//
//        })
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}