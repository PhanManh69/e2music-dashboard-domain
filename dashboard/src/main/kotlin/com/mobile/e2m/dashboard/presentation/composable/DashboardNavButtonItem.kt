package com.mobile.e2m.dashboard.presentation.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.mobile.e2m.core.ui.theme.E2MTheme

@Composable
internal fun DashboardBottomNavItem(
    selected: Boolean,
    icon: Int,
    name: String,
    isProfile: Boolean = false,
    onClick: () -> Unit,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Column(
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
        ) { onClick() },
        verticalArrangement = Arrangement.spacedBy(size.spacing.small4x),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isProfile) {
            Image(
                modifier = Modifier
                    .size(size.spacing.large3X)
                    .clip(CircleShape)
                    .border(
                        width = size.stroke.thickX,
                        color = if (selected) color.border.blur2Light else color.border.whiteDark,
                        shape = CircleShape,
                    ),
                painter = painterResource(icon),
                contentDescription = name,
                contentScale = ContentScale.Fit,
            )
        } else {
            Icon(
                modifier = Modifier.size(size.spacing.large3X),
                painter = painterResource(icon),
                contentDescription = name,
                tint = if (selected) color.surface.blur2Light else color.surface.whiteDark,
            )
        }

        Text(
            modifier = Modifier.width(size.spacing.large5x),
            text = name,
            style = if (selected) style.base.bold else style.base.medium,
            color = if (selected) color.text.blur2Light else color.text.whiteDark,
            textAlign = TextAlign.Center,
        )
    }
}
