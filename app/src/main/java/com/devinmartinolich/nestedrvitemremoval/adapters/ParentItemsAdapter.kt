package com.devinmartinolich.nestedrvitemremoval.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devinmartinolich.nestedrvitemremoval.R
import com.devinmartinolich.nestedrvitemremoval.interfaces.ItemListener
import com.devinmartinolich.nestedrvitemremoval.models.ParentItemModel

class ParentItemsAdapter(private val data: List<ParentItemModel>,
                         private var itemListener: ItemListener
) : RecyclerView.Adapter<ParentItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ParentItemsViewHolder(
            inflater,
            parent,
            itemListener
        )
    }

    override fun onBindViewHolder(holder: ParentItemsViewHolder, position: Int) {
        val card: ParentItemModel = data[position]
        holder.bind(card)
    }

    override fun getItemCount() = data.size
}

class ParentItemsViewHolder(inflater: LayoutInflater, parent: ViewGroup, itemListener: ItemListener) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.card_parent_item, parent, false)) {

    private var itemListener: ItemListener? = null
    private var cardTitle: TextView? = null
    private var recyclerItem: RecyclerView? = null

    init {
        cardTitle = itemView.findViewById(R.id.app_title)
        recyclerItem = itemView.findViewById(R.id.id_card_child_items)
        this.itemListener = itemListener
    }

    fun bind(card: ParentItemModel) {
        cardTitle?.setText("App: ${card.app_package}")

        recyclerItem?.apply {
            layoutManager = LinearLayoutManager(context)
            val itemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
            itemDecoration.setDrawable(context.getDrawable(R.drawable.divider_thin)!!)
            addItemDecoration(itemDecoration)
            adapter = ChildItemsAdapter(card.errors, itemListener!!)
        }
    }
}