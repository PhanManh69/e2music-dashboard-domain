package com.mobile.e2m.profile.presentation.profile.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.mobile.e2m.core.ui.composable.E2MButton
import com.mobile.e2m.core.ui.composable.E2MDialog
import com.mobile.e2m.core.ui.theme.E2MTheme
import com.mobile.e2m.profile.presentation.getString

@Composable
internal fun ProfileDialogLogout(
    modifier: Modifier = Modifier,
    openDialog: MutableState<Boolean> = mutableStateOf(false),
    signOutOnClick: () -> Unit = { },
) {
    val size = E2MTheme.alias.size
    val color = E2MTheme.alias.color
    val style = E2MTheme.typography

    if (openDialog.value) {
        E2MDialog(
            modifier = modifier,
            onDismissRequest = { openDialog.value = false },
            title = getString().signOutTxt,
            content = {
                Column(
                    modifier = Modifier
                        .padding(horizontal = size.spacing.small)
                        .padding(bottom = size.spacing.small),
                    verticalArrangement = Arrangement.spacedBy(size.spacing.small),
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = getString().doYouWantToLogOutTxt,
                        style = style.small.regular,
                        color = color.text.white,
                        textAlign = TextAlign.Center,
                    )

                    Row(
                        modifier = Modifier.padding(top = size.spacing.small2x),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        E2MButton(
                            modifier = Modifier.weight(1f),
                            title = getString().cancelTxt,
                            onClick = { openDialog.value = false }
                        )

                        Spacer(modifier = Modifier.width(size.spacing.small))

                        E2MButton(
                            modifier = Modifier.weight(1f),
                            title = getString().signOutTxt,
                            onClick = { signOutOnClick() }
                        )
                    }
                }
            }
        )
    }
}
