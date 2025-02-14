package com.mobile.e2m.profile.presentation.profile.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MSongsData
import com.mobile.e2m.core.ui.composable.E2MSongsItem
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.profile.presentation.getString

@Composable
internal fun ProfileLikes(
    modifier: Modifier = Modifier,
    items: List<E2MSongsData>,
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(size.spacing.small)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = getString().likesTxt,
                style = style.title.bold,
                color = color.text.white,
            )

            Text(
                text = getString().viewAllTxt,
                style = style.base.bold,
                color = color.text.blur2Light,
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(size.spacing.small2x)
        ) {
            repeat(items.size) {
                E2MSongsItem(
                    iconId = R.drawable.ic_menu_dots_vertical,
                    songItem = items[it],
                )
            }
        }
    }
}
