package com.victor.features_common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.victor.features_common.R

@Composable
fun PaginatedLazyColumn(
    pagingItems: LazyPagingItems<*>,
    itemContent: @Composable() (LazyItemScope.(Int) -> Unit),
) {
    LazyColumn {
        items(pagingItems.itemCount, itemContent = itemContent)

        when (pagingItems.loadState.source.append) {
            is LoadState.Loading -> item { PaginatedLoading() }
            is LoadState.Error -> item { PaginatedError { pagingItems.retry() } }
            else -> { }
        }
    }
}

@Composable
fun PaginatedLoading() {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
private fun PaginatedError(retry : () -> Unit) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(50.dp))
                .border(2.dp, Color.Gray, RoundedCornerShape(50.dp))
                .align(Alignment.Center)
                .clickable { retry.invoke() }
                .padding(4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_reload_24),
                colorFilter = ColorFilter.tint(Color.Gray),
                contentDescription = stringResource(id = R.string.content_description_feedback_image),
                modifier = Modifier.size(40.dp)
            )
        }
    }
}