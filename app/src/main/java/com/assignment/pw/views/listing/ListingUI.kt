package com.assignment.pw.views.listing

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import com.assignment.pw.R
import com.assignment.pw.listener.Listener
import com.assignment.pw.model.ItemModel
import com.assignment.pw.utils.CommonComposeUI.GlideCachedImage
import com.assignment.pw.utils.CommonComposeUI.setCustomSize
import com.assignment.pw.utils.ComposeExtensions.gridItems
import com.assignment.pw.utils.Loader.PulsatingLoader
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.placeholder
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow


object ListingUI {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ListUI(model: ListingViewModel,listener:Listener) {
        val loading by remember { model.loading }
        val pagerFlow = model.pagedData.collectAsLazyPagingItems()
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TopAppBar(
                title = { Text(text = "PW Assignment") })

            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                gridItems(pagerFlow.itemCount, nColumns = 2) { index ->
                    ListItemUI(
                        pagerFlow[index],
                        listener
                    )
                }
            }
        }
        if(loading){
            PulsatingLoader()
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun ListItemUI(item: ItemModel?,listener: Listener) {
        GlideCachedImage(
            modifier = Modifier
                .padding(setCustomSize(dim = com.intuit.sdp.R.dimen._10sdp))
                .fillMaxWidth()
                .height(setCustomSize(dim = com.intuit.sdp.R.dimen._150sdp))
                .shadow(5.dp,RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._10sdp)))
                .clip(RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._10sdp)))
                .clickable {
                    item?.id?.let {
                        listener.onItemClick(it)
                    }
                },
            model = item?.image ?: R.drawable.placeholder,
            loading = placeholder(R.drawable.placeholder),
            failure = placeholder(R.drawable.placeholder),
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
    }
}