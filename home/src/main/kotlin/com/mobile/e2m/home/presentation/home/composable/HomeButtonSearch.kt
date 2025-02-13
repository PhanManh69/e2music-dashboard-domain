package com.mobile.e2m.home.presentation.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.mobile.e2m.core.ui.composable.debounceClickable
import com.mobile.e2m.core.ui.composable.shadowCustom
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.core.ui.R
import com.mobile.e2m.core.ui.composable.E2MIcon
import com.mobile.e2m.home.presentation.getString

@Composable
internal fun HomeButtonSearch(
    modifier: Modifier = Modifier,
    paddingEnd: Dp = E2MTheme.alias.size.spacing.small,
    onClick: () -> Unit = { },
) {
    val style = E2MTheme.typography
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = size.spacing.small4x)
            .padding(end = paddingEnd)
            .shadowCustom(
                color = color.surface.shadowDark,
                shapeRadius = size.radius.radius7,
                blurRadius = size.spacing.small4x
            )
            .background(
                color = color.surface.textField,
                shape = RoundedCornerShape(size.radius.radius7),
            )
            .border(
                width = size.stroke.thick,
                color = color.border.textField,
                shape = RoundedCornerShape(size.radius.radius7)
            )
            .debounceClickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(size.spacing.small)
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = size.spacing.smallX)
                .padding(start = size.spacing.small)
        ) {
            E2MIcon(
                modifier = Modifier.size(size.icon.small1X),
                iconId = R.drawable.ic_search,
                tint = color.icon.black
            )
        }

        Text(
            text = getString().searchSongTxt,
            style = style.title.regular,
            color = color.text.textFieldPlaceholder
        )
    }
}

@Preview
@Composable
fun HomeButtonSearchPreview() {
    HomeButtonSearch()
}
