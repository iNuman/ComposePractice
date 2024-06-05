package com.example.composepractice.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.compose.ui.util.lerp
import com.example.composepractice.R
import com.example.composepractice.ui.theme.ComposePracticeTheme

@Composable
fun ProfileScreen() {
    ComposePracticeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Profile Screen", style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .padding(start = 24.dp, bottom = 16.dp, end = 16.dp, top = 32.dp)
                        .align(Alignment.Start)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.large)
                ) {
                    ParallaxSwiper()
                }
            }
        }
    }
}

@Composable
fun ParallaxSwiper(
    viewPortFraction: Float = 0.85f,
    backgroundZoomEnabled: Boolean = true,
    foregroundFadeEnabled: Boolean = true,
    initialPage: Int = 0,
    initialPageOffsetFraction: Float = 0f
) {
    val images = listOf(
        painterResource(id = R.drawable.image1),
        painterResource(id = R.drawable.image2),
        painterResource(id = R.drawable.image3),
        painterResource(id = R.drawable.image4),
    )
    val pagerState = rememberPagerState(
        pageCount = { images.size },
        initialPage = initialPage,
        initialPageOffsetFraction = initialPageOffsetFraction
    )
    HorizontalPager(
        state = pagerState,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = (1f - viewPortFraction) * .5f * LocalConfiguration.current.screenWidthDp.dp),
    ) { page ->
        val pageOffset = calculateCurrentOffsetForPage(page, pagerState)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.8f)
                .graphicsLayer {
                    if (backgroundZoomEnabled) {
                        val scale = lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                        scaleX = scale
                        scaleY = scale
                    }
                    if (foregroundFadeEnabled) {
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                }
        ) {
            Image(
                painter = images[page],
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun calculateCurrentOffsetForPage(page: Int, pagerState: PagerState): Float {
    return (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
}

@Composable
@Preview
fun ProfileScreenPreview() {
    ProfileScreen()
}