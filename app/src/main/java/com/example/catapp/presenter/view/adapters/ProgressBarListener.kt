package com.example.catapp.presenter.view.adapters

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ProgressBar
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.catapp.data.models.CatPhoto
import com.example.catapp.presenter.viewModel.CatViewModel

class ProgressBarListener(
    private val progressbar: ProgressBar,
    private val catViewModel: CatViewModel,
) :
    RequestListener<Drawable?> {

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable?>?,
        isFirstResource: Boolean,
    ): Boolean {
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable?>?,
        dataSource: DataSource?,
        isFirstResource: Boolean,
    ): Boolean {
        getBitmapFromResource(resource)
        progressbar.visibility = View.GONE
        return false
    }

    private fun getBitmapFromResource(resource: Drawable?) {
        catViewModel.insert(CatPhoto(resource?.toBitmap()))
    }
}
