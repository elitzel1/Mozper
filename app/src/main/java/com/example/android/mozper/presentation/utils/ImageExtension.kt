package com.example.android.mozper.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadFromUrl(url: String?, placeholder: Int) {
    Glide.with(this.context)
        .load(url)
        .placeholder(placeholder)
        .into(this)
}
