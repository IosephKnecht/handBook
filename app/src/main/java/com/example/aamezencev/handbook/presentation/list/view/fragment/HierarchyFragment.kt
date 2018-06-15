package com.example.aamezencev.handbook.presentation.list.view.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.IElement
import com.example.aamezencev.handbook.data.elementOf
import com.example.aamezencev.handbook.databinding.HierarchyFragmentBinding
import com.example.aamezencev.handbook.presentation.list.view.adapter.HierarchyAdapter
import com.example.aamezencev.handbook.presentation.list.viewModel.HierarchyViewModel
import kotlinx.android.synthetic.main.hierarchy_fragment.*

class HierarchyFragment : Fragment() {
    companion object {
        fun instanceFragment() {

        }
    }

    private lateinit var hierarchyAdapter: HierarchyAdapter
    private lateinit var binding: HierarchyFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.hierarchy_fragment, container, false)
        binding.viewModel = HierarchyViewModel()
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hierarchyAdapter = HierarchyAdapter()

        hierarchyView.apply {
            layoutManager = LinearLayoutManager(this@HierarchyFragment.context)
            setHasFixedSize(true)
            adapter = hierarchyAdapter
        }

        val hierarchy = elementOf {
            name = "Chapter 1"
            childs {
                child {
                    name = "Chapter 2"
                    childs {
                        child {
                            name = "Page 1"
                        }
                    }
                }
                child {
                    name = "Page 2"
                }
            }
        }

        val list = mutableListOf<IElement>()

        hierarchy.createIterator().forEach {
            list.add(it)
        }

        binding.viewModel?.hierarchyList = list

        //binding.viewModel.hierarchyList = list.toList()
    }
}