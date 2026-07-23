package com.comfortcross.liturgy.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comfortcross.liturgy.R

/**
 * The app's motif: a photograph of a small handheld olive-wood comfort cross, cut out
 * onto transparency. Sized by the caller's [modifier]; the whole app takes its identity
 * from this held cross.
 */
@Composable
fun ComfortCross(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(R.drawable.comfort_cross),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier,
    )
}

@Composable
fun ComfortCrossMark(size: Dp = 40.dp, modifier: Modifier = Modifier) {
    ComfortCross(modifier = modifier.size(size))
}
