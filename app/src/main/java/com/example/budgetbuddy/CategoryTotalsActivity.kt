package com.example.budgetbuddy

import android.graphics.Color
import android.os.Bundle
import android.widget.ExpandableListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class CategoryTotalsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_totals2)

        val expandableListView =
            findViewById<ExpandableListView>(R.id.main

        val categoryList = listOf(
            "Starbucks",
            "KFC",
            "Rocomamas",
            "WiFi Monthly",
            "Furniture",
            "Salary"
        )

        val detailsMap = hashMapOf<String, List<String>>()

        detailsMap["Starbucks"] = listOf(
            "Date: 02 May 2026",
            "Location: Musgrave Centre",
            "Expense: -R145.00"
        )

        detailsMap["KFC"] = listOf(
            "Date: 04 May 2026",
            "Location: Gateway Mall",
            "Expense: -R220.00"
        )

        detailsMap["Rocomamas"] = listOf(
            "Date: 08 May 2026",
            "Location: Pavilion",
            "Expense: -R380.00"
        )

        detailsMap["WiFi Monthly"] = listOf(
            "Date: 10 May 2026",
            "Provider: Vodacom Fibre",
            "Expense: -R799.00"
        )

        detailsMap["Furniture"] = listOf(
            "Date: 12 May 2026",
            "Store: @Home",
            "Expense: -R2450.00"
        )

        detailsMap["Salary"] = listOf(
            "Date: 15 May 2026",
            "Company: ABC Solutions",
            "Income: +R15000.00"
        )

        val adapter = object : android.widget.BaseExpandableListAdapter() {

            override fun getGroupCount(): Int {
                return categoryList.size
            }

            override fun getChildrenCount(groupPosition: Int): Int {
                return detailsMap[categoryList[groupPosition]]!!.size
            }

            override fun getGroup(groupPosition: Int): Any {
                return categoryList[groupPosition]
            }

            override fun getChild(groupPosition: Int, childPosition: Int): Any {
                return detailsMap[categoryList[groupPosition]]!![childPosition]
            }

            override fun getGroupId(groupPosition: Int): Long {
                return groupPosition.toLong()
            }

            override fun getChildId(groupPosition: Int, childPosition: Int): Long {
                return childPosition.toLong()
            }

            override fun hasStableIds(): Boolean {
                return false
            }

            override fun getGroupView(
                groupPosition: Int,
                isExpanded: Boolean,
                convertView: android.view.View?,
                parent: android.view.ViewGroup?
            ): android.view.View {

                val layout = layoutInflater.inflate(
                    android.R.layout.simple_expandable_list_item_2,
                    null
                )

                val title =
                    layout.findViewById<android.widget.TextView>(
                        android.R.id.text1
                    )

                val amount =
                    layout.findViewById<android.widget.TextView>(
                        android.R.id.text2
                    )

                val category = categoryList[groupPosition]

                title.text = category

                when (category) {

                    "Salary" -> {
                        amount.text = "+ R15 000.00"
                        amount.setTextColor(Color.parseColor("#4CAF50"))
                    }

                    "Furniture" -> {
                        amount.text = "- R2450.00"
                        amount.setTextColor(Color.RED)
                    }

                    "WiFi Monthly" -> {
                        amount.text = "- R799.00"
                        amount.setTextColor(Color.RED)
                    }

                    "Rocomamas" -> {
                        amount.text = "- R380.00"
                        amount.setTextColor(Color.RED)
                    }

                    "KFC" -> {
                        amount.text = "- R220.00"
                        amount.setTextColor(Color.RED)
                    }

                    else -> {
                        amount.text = "- R145.00"
                        amount.setTextColor(Color.RED)
                    }
                }

                title.textSize = 18f
                amount.textSize = 16f

                return layout
            }

            override fun getChildView(
                groupPosition: Int,
                childPosition: Int,
                isLastChild: Boolean,
                convertView: android.view.View?,
                parent: android.view.ViewGroup?
            ): android.view.View {

                val textView = android.widget.TextView(this@CategoryTotalsActivity)

                textView.text =
                    detailsMap[categoryList[groupPosition]]!![childPosition]

                textView.setPadding(80, 20, 20, 20)

                textView.textSize = 16f

                textView.setTextColor(Color.DKGRAY)

                return textView
            }

            override fun isChildSelectable(
                groupPosition: Int,
                childPosition: Int
            ): Boolean {
                return true
            }
        }

        expandableListView.setAdapter(adapter)
    }
}