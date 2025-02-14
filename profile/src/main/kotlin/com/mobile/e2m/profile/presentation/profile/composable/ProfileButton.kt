package com.mobile.e2m.profile.presentation.profile.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MButton
import com.mobile.e2m.core.ui.composable.E2MIcon
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.profile.presentation.getString

@Composable
internal fun ProfileButton(
    modifier: Modifier = Modifier,
    items: List<Int>,
) {
    val context = LocalContext.current
    val size = E2MTheme.alias.size

    Column(
        modifier = modifier.padding(horizontal = size.spacing.small),
        verticalArrangement = Arrangement.spacedBy(size.spacing.small)
    ) {
        repeat(items.size) {
            ProfileButtonItem(
                title = context.getString(items[it])
            )
        }

        E2MButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = size.spacing.small2x),
            title = getString().signOutTxt,
        )
    }
}

@Composable
private fun ProfileButtonItem(
    modifier: Modifier = Modifier,
    title: String? = null,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(size.spacing.small2x),
    ) {
        title?.let {
            Text(
                modifier = Modifier.weight(1f),
                text = it,
                style = style.title.bold,
                color = color.text.white,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        E2MIcon(
            modifier = Modifier.size(size.icon.small2X),
            iconId = R.drawable.ic_angle_right,
            tint = color.icon.blue2Light,
        )
    }
}
