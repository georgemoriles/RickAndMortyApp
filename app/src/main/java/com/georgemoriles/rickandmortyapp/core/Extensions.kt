package com.georgemoriles.rickandmortyapp.core

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide


fun Activity.toast(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}

private fun ImageView.load(url: Any) {
    url.let {
        Glide.with(this.context).load(url).into(this)
    }
}

fun View.toggleVisibility() {
    visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
}
