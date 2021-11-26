package com.example.studio.presentaion.bindingadapters;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {
    @BindingAdapter("isImage")
    public static void isImage(View view, boolean isImage) {
        if (isImage)
            view.setVisibility(View.VISIBLE);
        else
            view.setVisibility(View.GONE);
    }

}
