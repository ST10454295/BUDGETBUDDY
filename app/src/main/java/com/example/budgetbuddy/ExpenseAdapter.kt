package com.example.budgetbuddy

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExpenseAdapter(
    private val list: ArrayList<ExpenseModel>
) : RecyclerView.Adapter<ExpenseAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val amount: TextView =
            view.findViewById(R.id.txtAmount)

        val description: TextView =
            view.findViewById(R.id.txtDescription)

        val category: TextView =
            view.findViewById(R.id.txtCategory)

        val date: TextView =
            view.findViewById(R.id.txtDate)

        val image: ImageView =
            view.findViewById(R.id.imgReceipt)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.expense_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {

        val item = list[position]

        holder.amount.text = "R ${item.amount}"

        holder.description.text = item.description

        holder.category.text =
            "Category: ${item.category}"

        holder.date.text =
            "Date: ${item.date}"

        // IMAGE
        if (item.image.isNotEmpty()) {

            holder.image.setImageURI(
                Uri.parse(item.image)
            )
        }
    }

    override fun getItemCount(): Int {

        return list.size
    }
}