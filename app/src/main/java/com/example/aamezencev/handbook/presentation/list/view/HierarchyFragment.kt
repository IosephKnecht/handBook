package com.example.aamezencev.handbook.presentation.list.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.aamezencev.handbook.R
import com.example.aamezencev.handbook.data.IElement
import com.example.aamezencev.handbook.data.elementOf
import com.example.aamezencev.handbook.presentation.list.view.adapter.HierarchyAdapter
import kotlinx.android.synthetic.main.hierarchy_fragment.*

class HierarchyFragment : Fragment() {
    companion object {
        fun instanceFragment() {

        }
    }

    private lateinit var hierarchyAdapter: HierarchyAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.hierarchy_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

        hierarchyAdapter = HierarchyAdapter()
        hierarchyAdapter.elementList = list

        hierarchyView.apply {
            layoutManager = LinearLayoutManager(this@HierarchyFragment.context)
            setHasFixedSize(true)
            adapter = hierarchyAdapter
        }
    }
}