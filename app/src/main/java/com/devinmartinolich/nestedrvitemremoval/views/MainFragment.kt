package com.devinmartinolich.nestedrvitemremoval.views

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.devinmartinolich.nestedrvitemremoval.R
import com.devinmartinolich.nestedrvitemremoval.adapters.ParentItemsAdapter
import com.devinmartinolich.nestedrvitemremoval.controllers.MainActivity
import com.devinmartinolich.nestedrvitemremoval.databinding.FragmentMainBinding
import com.devinmartinolich.nestedrvitemremoval.interfaces.ItemListener
import com.devinmartinolich.nestedrvitemremoval.models.ChildItemModel
import com.devinmartinolich.nestedrvitemremoval.models.ParentItemModel

class MainFragment : Fragment(), ItemListener{
    companion object {
        private const val TAG = "MainFragment"
    }

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private var mActivity : MainActivity? = null

    var mView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "-> onCreateView()")
        super.onCreateView(inflater, container, savedInstanceState)

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        mView = binding.root
        mActivity = (activity as MainActivity)

        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "-> onViewCreated()")
        super.onViewCreated(view, savedInstanceState)

        addFakeData()
    }

    fun addFakeData() {

        val items = arrayListOf(
            ChildItemModel("NullPointerException", "01/01/2019"),
            ChildItemModel("ClassNotFoundException", "01/03/2019")
        )

        val items2 = arrayListOf(
            ChildItemModel("NoSuchMethodException", "01/02/2019"),
            ChildItemModel("RuntimeException", "01/15/2020")
        )

        val items3 = arrayListOf(
            ChildItemModel("IOException", "01/01/2019"),
            ChildItemModel("NumberFormatException", "01/03/2019")
        )

        val cards = listOf(
            ParentItemModel("com.example.notes", items),
            ParentItemModel("com.example.recyclerviewtest", items2),
            ParentItemModel("com.example.nestedrvitemremoval", items3)
        )

        binding.idCardParentItems?.apply {
            layoutManager = LinearLayoutManager(activity as Activity)
            val itemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            itemDecoration.setDrawable(context.getDrawable(R.drawable.divider_thick)!!)
            addItemDecoration(itemDecoration)
            adapter =
                ParentItemsAdapter(
                    cards,
                    this@MainFragment
                )
        }
    }

    override fun onViewCard(position: Int) {
        Log.d(TAG, "-> onViewCard()")
    }

    override fun onRemoveCard(position: Int) {
        Log.d(TAG, "-> onRemoveCard()")
    }
}