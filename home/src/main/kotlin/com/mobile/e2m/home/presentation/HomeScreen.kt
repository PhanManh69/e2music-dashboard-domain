package com.mobile.e2m.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mobile.e2m.core.ui.composable.E2MHeader
import com.mobile.e2m.core.ui.composable.E2MScaffold
import com.mobile.e2m.core.ui.composable.background.E2MBackgroundDark
import com.mobile.e2m.core.ui.theme.Write100

@Composable
internal fun HomeScreen() {
    Box {
        E2MBackgroundDark()

        E2MScaffold(
            topBar = {
                E2MHeader(
                    title = "Home Screen",
                )
            },
            content = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Đây là màn hình trang chủ",
                        color = Write100
                    )
                }
            }
        )
    }
}
