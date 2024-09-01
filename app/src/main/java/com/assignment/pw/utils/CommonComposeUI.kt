package com.assignment.pw.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.DefaultAlpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.Placeholder
import com.bumptech.glide.load.engine.DiskCacheStrategy

object CommonComposeUI {

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun GlideCachedImage(
        model: Any?,
        contentDescription: String?,
        modifier: Modifier = Modifier,
        alignment: Alignment = Alignment.Center,
        contentScale: ContentScale = ContentScale.Fit,
        alpha: Float = DefaultAlpha,
        colorFilter: ColorFilter? = null,
        loading: Placeholder? = null,
        failure: Placeholder? = null,
    ) {
        GlideImage(
            modifier = modifier,
            loading = loading,
            failure = failure,
            model = model,
            contentScale = contentScale,
            contentDescription = contentDescription,
            alignment = alignment,
            colorFilter = colorFilter,
            alpha = alpha
        ) {
            it.diskCacheStrategy(DiskCacheStrategy.ALL)
        }
    }

    @Composable
    fun setCustomSize(dim: Int): Dp {
        return dimensionResource(dim)
    }

    @Composable
    fun setCustomTextSize(dim: Int): TextUnit {
        return dimensionResource(dim).value.sp
    }

}