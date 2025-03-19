package com.example.webview.customviews

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemSpacingDecorator(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        // Set margins to all sides of the item (top, left, right, and bottom)
        outRect.top = spacing
        outRect.left = 0
        outRect.right = 0
        outRect.bottom = spacing
    }
}