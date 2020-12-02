package com.example.coordinatorlayoutdemo.behaviors

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar


class ShrinkBehavior(context: Context, attributeSet: AttributeSet) : CoordinatorLayout.Behavior<View>(context, attributeSet) {

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is Snackbar.SnackbarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        super.onDependentViewChanged(parent, child, dependency)

        val distanceY = getViewOffsetForSnackbar(parent, child)
        val fractionComplete = distanceY / dependency.height
        val scaleFactor = 1 - fractionComplete

        child.scaleX = scaleFactor
        child.scaleY = scaleFactor
        return true
    }

    private fun getViewOffsetForSnackbar(parent: CoordinatorLayout, view: View): Float {
        var maxOffset = 0f
        val dependencies = parent.getDependencies(view)

        dependencies.forEach { dependency ->
            if (dependency is Snackbar.SnackbarLayout && parent.doViewsOverlap(view, dependency)) {
                maxOffset = Math.max(maxOffset, (dependency.translationY - dependency.height) * -1)
            }
        }
        return maxOffset
    }
}
