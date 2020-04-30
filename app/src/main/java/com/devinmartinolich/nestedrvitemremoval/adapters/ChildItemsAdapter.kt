package com.devinmartinolich.nestedrvitemremoval.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.devinmartinolich.nestedrvitemremoval.R
import com.devinmartinolich.nestedrvitemremoval.interfaces.ItemListener
import com.devinmartinolich.nestedrvitemremoval.models.ChildItemModel

class ChildItemsAdapter(private val data: ArrayList<ChildItemModel>,
                        private var itemListener: ItemListener
) : RecyclerView.Adapter<ChildItemsViewHolder>(), ItemListener {

    companion object{
        private const val TAG = "ChildItemsAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ChildItemsViewHolder(inflater, parent, this)
    }

    override fun onBindViewHolder(holder: ChildItemsViewHolder, position: Int) {
        val card: ChildItemModel = data[position]
        holder.bind(card)
    }

    override fun getItemCount() = data.size

    // We dont want this here but we trap if for the onRemoveCard below
    // Use the calling class listener
    override fun onViewCard(position: Int) {
        itemListener?.onViewCard(position)
    }

    override fun onRemoveCard(position: Int) {
        Log.d(TAG, "-> onRemoveCard()")
        data.removeAt(position)
        notifyDataSetChanged()
        itemListener?.onRemoveCard(position)
    }
}

class ChildItemsViewHolder(inflater: LayoutInflater, parent: ViewGroup, itemListener: ItemListener) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.card_child_item, parent, false)), View.OnClickListener {

    companion object{
        private const val TAG = "ChildItemsViewHolder"
    }

    private var itemListener: ItemListener? = null
    private var cardTitle: TextView? = null
    private var cardDesc: TextView? = null
    private var remove: ConstraintLayout? = null

    init {
        cardTitle = itemView.findViewById(R.id.card_title)
        cardDesc = itemView.findViewById(R.id.card_desc)
        remove = itemView.findViewById(R.id.remove)
        this.itemListener = itemListener
    }

    fun bind(card: ChildItemModel) {
        cardTitle?.text = card.error_type
        cardDesc?.setText("Reported: ${card.reported_date}")

        itemView.setOnClickListener(this)
        remove?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Log.d(TAG, "-> onClick()")

        when (v?.id) {
            R.id.remove -> itemListener?.onRemoveCard(adapterPosition)
            else -> itemListener?.onViewCard(adapterPosition)
        }
    }
}