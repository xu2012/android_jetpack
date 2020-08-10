package com.xu.material

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xu.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : BaseFragment() {
    private val data = arrayOf(
        "Button",
        "Text",
        "EditText",
        "RadioButton",
        "CheckBox",
        "BottomNavigationView",
        "BottomSheetDialog"
    )

    override fun layoutRes(): Int {
        return R.layout.fragment_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        val callback =
            requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
                OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    println("-------handleOnBackPressed-------")
                }

            })
        // Handle the back button event

    }

    private fun initData() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = ListAdapter(data)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : ListAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                when (position) {
                    0 -> {
                        findNavController().navigate(R.id.action_listFragment_to_matrialButtonFragment)
                    }
                    else -> {
                        Toast.makeText(context, "click $position", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}