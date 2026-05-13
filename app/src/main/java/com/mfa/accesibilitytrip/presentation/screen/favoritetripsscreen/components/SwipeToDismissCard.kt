package com.mfa.accesibilitytrip.presentation.screen.favoritetripsscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mfa.accesibilitytrip.R
import com.mfa.accesibilitytrip.presentation.model.TripCardUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SwipeToDismissCard(
    trip: TripCardUiModel,
    index: Int,
    onClick: () -> Unit,
    onSwipeToDelete: () -> Unit,
) {
    val dismissState = rememberSwipeToDismissBoxState()

    LaunchedEffect(dismissState.currentValue) {
        if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
            onSwipeToDelete()
            dismissState.snapTo(SwipeToDismissBoxValue.Settled)
        }
    }

    SwipeToDismissBox(
        state = dismissState,
        enableDismissFromStartToEnd = false,
        enableDismissFromEndToStart = true,
        backgroundContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color(0xFFFF6B6B),
                        shape = RoundedCornerShape(8.dp),
                    )
                    .padding(horizontal = 24.dp),
                contentAlignment = Alignment.CenterEnd,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_heart_filled),
                        contentDescription = stringResource(R.string.remove_action),
                        modifier = Modifier.size(24.dp),
                        tint = Color.White,
                    )
                    Text(
                        text = stringResource(R.string.remove_action),
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White,
                    )
                }
            }
        },
    ) {
        TripOverviewCard(
            trip = trip,
            index = index,
            onClick = onClick,
        )
    }
}