package com.example.composepractice.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.AsyncImage
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
                    .fillMaxSize()
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Profile Screen",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall,
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
        initialPage: Int = 0,
        initialPageOffsetFraction: Float = 0f
    ) {
        val images = listOf(
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5
        )

        val pagerState = rememberPagerState(
            initialPage = initialPage,
            pageCount = { images.size },
            initialPageOffsetFraction = initialPageOffsetFraction
        )
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        val contentPadding = (screenWidth * (1f - viewPortFraction) / 2)

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = contentPadding)
        ) { page ->
            val pageOffset = calculateCurrentOffsetForPage(page, pagerState)
            SwiperItem(
                image = images[page],
                pageOffset = pageOffset
            )
        }
    }

    @Composable
    fun SwiperItem(
        image: Int,
        pageOffset: Float,
    ) {

        val translationFraction = if (pageOffset >= 0) {
            pageOffset * 0.2f // Swipe from left to right
        } else {
            pageOffset * -0.2f // Swipe from right to left
        }

        val scale = lerp(
            start = 2.0f, // this can be adjusted to change the scaling of the image
            stop = 1f,
            fraction = 1f - translationFraction.coerceIn(0f, 1f)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.Black)
                .aspectRatio(1f)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .clip(MaterialTheme.shapes.large)
            ) {

                AsyncImage(
                    model = image,
                    contentDescription = "Scene image",
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                            translationX = translationFraction * size.width * 0.3f
                        },

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