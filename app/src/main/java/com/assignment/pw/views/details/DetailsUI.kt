package com.assignment.pw.views.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.assignment.pw.R
import com.assignment.pw.utils.CommonComposeUI.GlideCachedImage
import com.assignment.pw.utils.CommonComposeUI.setCustomSize
import com.assignment.pw.utils.CommonComposeUI.setCustomTextSize
import com.assignment.pw.utils.ComposeExtensions.gridItems
import com.assignment.pw.utils.Loader.PulsatingLoader
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.placeholder

object DetailsUI {

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
    @Composable
    fun DetailUI(model: DetailsViewModel, onBack: () -> Unit) {
        val item by remember {
            model.item
        }
        val loading by remember {
            model.loading
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            TopAppBar(
                title = { Text(text = item?.name ?: "Item Details") },
                navigationIcon = {
                    Image(
                        modifier = Modifier.clickable { onBack.invoke() },
                        painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                        contentDescription = null
                    )
                })

            item?.image?.let {
                GlideCachedImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(setCustomSize(dim = com.intuit.sdp.R.dimen._200sdp))
                        .background(Color.Gray),
                    model = it,
                    loading = placeholder(R.drawable.placeholder),
                    failure = placeholder(R.drawable.placeholder),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
                Spacer(modifier = Modifier.height(setCustomSize(com.intuit.sdp.R.dimen._16sdp)))
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(setCustomSize(com.intuit.sdp.R.dimen._16sdp))
            ) {
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {

                        Text(
                            text = "Name: ${item?.name ?: "N/A"}",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(vertical = setCustomSize(com.intuit.sdp.R.dimen._4sdp))
                        )

                        Text(
                            text = "Status: ${item?.status ?: "N/A"}",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(vertical = setCustomSize(com.intuit.sdp.R.dimen._4sdp))
                        )

                        Text(
                            text = "Species: ${item?.species ?: "N/A"}",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(vertical = setCustomSize(com.intuit.sdp.R.dimen._4sdp))
                        )

                        Text(
                            text = "Gender: ${item?.gender ?: "N/A"}",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(vertical = setCustomSize(com.intuit.sdp.R.dimen._4sdp))
                        )

                        item?.origin?.name?.let {
                            Text(
                                text = "Origin: $it",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(vertical = setCustomSize(com.intuit.sdp.R.dimen._4sdp))
                            )
                        }

                        item?.location?.name?.let {
                            Text(
                                text = "Location: $it",
                                style = MaterialTheme.typography.bodyLarge,
                                modifier = Modifier.padding(vertical = setCustomSize(com.intuit.sdp.R.dimen._4sdp))
                            )
                        }
                    }
                }
                item?.episode?.let { episodes ->
                    item {
                        Text(
                            text = "Episodes",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(vertical = setCustomSize(com.intuit.sdp.R.dimen._8sdp))
                        )
                        Spacer(modifier = Modifier.height(setCustomSize(com.intuit.sdp.R.dimen._16sdp)))
                    }
                    gridItems(episodes.size, 2) {
                        EpisodeListItem(epiNo = (it + 1))
                    }
                }
            }

        }

        if (loading) {
            PulsatingLoader()
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun EpisodeListItem(epiNo: Int) {
        Box(
            modifier = Modifier
                .padding(setCustomSize(dim = com.intuit.sdp.R.dimen._10sdp))
                .fillMaxWidth()
                .height(setCustomSize(dim = com.intuit.sdp.R.dimen._150sdp))
                .shadow(
                    5.dp,
                    RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._10sdp))
                )
                .clip(RoundedCornerShape(setCustomSize(dim = com.intuit.sdp.R.dimen._10sdp)))

        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Ep. $epiNo",
                fontSize = setCustomTextSize(dim = com.intuit.ssp.R.dimen._16ssp),
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
            )
        }
    }
}