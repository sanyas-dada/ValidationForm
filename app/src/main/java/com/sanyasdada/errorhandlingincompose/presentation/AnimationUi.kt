package com.sanyasdada.errorhandlingincompose.presentation

import androidx.compose.animation.*
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sanyasdada.errorhandlingincompose.ui.theme.Chocolate

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationUi() {
    // Create a MutableTransitionState<Boolean> for the AnimatedVisibility.
    val state = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }
    Column {
        AnimatedVisibility(visibleState = state) {
            Text(text = "Hello, world!")
        }

        // Use the MutableTransitionState to know the current animation state
        // of the AnimatedVisibility.
        Text(
            text = when {
                state.isIdle && state.currentState -> "Visible"
                !state.isIdle && state.currentState -> "Disappearing"
                state.isIdle && !state.currentState -> "Invisible"
                else -> "Appearing"
            }
        )
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimationTesting() {
    val visible: Boolean = true
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        // Fade in/out the background and the foreground.
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.DarkGray)
        ) {
            Box(
                Modifier
                    .align(Alignment.Center)
                    .animateEnterExit(
                        // Slide in/out the inner box.
                        enter = slideInVertically(),
                        exit = slideOutVertically()
                    )
                    .sizeIn(minWidth = 256.dp, minHeight = 64.dp)
                    .background(Color.Red),
                contentAlignment = Alignment.Center
            ) {
                // Content of the notification…
                Text("All is well")
            }
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var count by remember { mutableStateOf(0) }
        var message by remember { mutableStateOf("Hello") }
        Button(onClick = { count++ }) {
            Text("Add")
        }
        AnimatedContent(
            targetState = count
        ) { targetCount ->
            // Make sure to use `targetCount`, not `count`.
            Text(text = "Count: $targetCount")
        }
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(10.dp,0.dp,10.dp,0.dp))
                .background(color= Chocolate)
                .animateContentSize(),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = message,
                color=Color.Red,
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp
            )
        }
    }
}

@Preview
@Composable
fun ShowAnimation() {
    AnimationTesting()
}