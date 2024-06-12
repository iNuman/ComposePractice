package com.example.composepractice.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ContextualFlowRow
import androidx.compose.foundation.layout.ContextualFlowRowOverflow
import androidx.compose.foundation.layout.ContextualFlowRowOverflowScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composepractice.R
import com.example.composepractice.ui.theme.ComposePracticeTheme

@Composable
fun HomeScreen() {
    ComposePracticeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.padding(vertical = 24.dp))
                AnimatedTextSwitcher()
                Spacer(modifier = Modifier.padding(vertical = 8.dp))
                CreativeContextualFlowLayoutExample()
            }
        }
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreativeContextualFlowLayoutExample() {
    val hobbies = listOf(
        "Photography", "Traveling", "Reading", "Cooking", "Gardening",
        "Painting", "Writing", "Dancing", "Gaming", "Fishing",
        "Hiking", "Cycling", "Swimming", "Running", "Yoga",
        "Meditation", "Sculpting", "Singing", "Astronomy", "Bird Watching",
        "Collecting", "Knitting", "Sewing", "Model Building", "Origami",
        "Magic Tricks", "Calligraphy", "Board Games", "Puzzles", "Martial Arts",
        "Rock Climbing", "Surfing", "Skiing", "Snowboarding", "Skateboarding",
        "Baking", "Brewing", "Woodworking", "Metalworking", "Leathercraft"
    )
    var maxLines by remember {
        mutableIntStateOf(4)
    }

    val moreOrCollapseIndicator = @Composable { scope: ContextualFlowRowOverflowScope ->
        val remainingItems = hobbies.size - scope.shownItemCount
        ChipItem(if (remainingItems == 0) "Less" else "+$remainingItems", onClick = {
            if (remainingItems == 0) {
                maxLines = 4
            } else {
                maxLines += 5
            }
        })
    }

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color.Gray.copy(0.2f), Color.White.copy(alpha = 0.2f))
    )

    Column(
        modifier = Modifier
            .safeDrawingPadding()
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "My Hobbies",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(MaterialTheme.shapes.large)
        ) {
            ContextualFlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(align = Alignment.Top)
                    .background(brush = gradientBrush)
                    .padding(16.dp)
                    .clip(MaterialTheme.shapes.large)
                    .animateContentSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                maxLines = maxLines,
                overflow = ContextualFlowRowOverflow.expandOrCollapseIndicator(
                    minRowsToShowCollapse = 4,
                    expandIndicator = moreOrCollapseIndicator,
                    collapseIndicator = moreOrCollapseIndicator
                ),
                itemCount = hobbies.size
            ) { index ->
                ChipItem(hobbies[index])
            }
        }
    }
}

@Composable
fun ChipItem(text: String, onClick: (() -> Unit)? = null) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(Color.LightGray.copy(0.7f))
            .clickable { onClick?.invoke() }
            .padding(horizontal = 12.dp, vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, color = Color.White, style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
@Preview
fun HomeScreenPreview() {
    HomeScreen()
}