package com.example.webview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.webview.R
import com.example.webview.model.ComponentsModel
import com.google.android.material.textview.MaterialTextView

class WebConfigAdapter(private val list: List<ComponentsModel>, val context: Context) :
    RecyclerView.Adapter<WebConfigAdapter.WebConfigViewHolder>() {
    private var expandedPosition = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WebConfigViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.web_config_items, parent, false)
        return WebConfigViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WebConfigViewHolder, position: Int) {
        val currentItem = list[position]
        holder.components.text = currentItem.componentName

        if (currentItem.expandable) {
            holder.expandableLayout.visibility = View.VISIBLE
            holder.radioGroup.removeAllViews()
            addRadioButtons(currentItem, holder)
        } else {
            holder.expandableLayout.visibility = View.GONE
        }

        holder.components.setOnClickListener {
            if (expandedPosition == position) {
                currentItem.expandable = false
                expandedPosition = -1
            } else {
                if (expandedPosition != -1) {
                    list[expandedPosition].expandable = false
                    notifyItemChanged(expandedPosition)
                }

                currentItem.expandable = true
                expandedPosition = position
            }
            notifyItemChanged(position)
        }
    }

    private fun addRadioButtons(currentItem: ComponentsModel, holder: WebConfigViewHolder) {
        for (item in currentItem.componentList) {
            val radioButton = RadioButton(context)
            val radioGroupParams: RadioGroup.LayoutParams = RadioGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            radioGroupParams.setMargins(48, 0, 0, 0)
            radioButton.layoutParams = radioGroupParams
            radioButton.text = item

            if (item == currentItem.selectedValue) {
                radioButton.isChecked = true
            }
            radioButton.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    currentItem.selectedValue = item
                }
            }
            holder.radioGroup.addView(radioButton)
        }
    }

    class WebConfigViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val components: MaterialTextView = itemView.findViewById(R.id.style_components)
        val expandableLayout: ConstraintLayout = itemView.findViewById(R.id.expandable_layout)
        val radioGroup: RadioGroup = itemView.findViewById(R.id.rg_components)
    }
}