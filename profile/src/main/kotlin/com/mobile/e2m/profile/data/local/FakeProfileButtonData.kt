package com.mobile.e2m.profile.data.local

import com.mobile.e2m.core.ui.R
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

fun fakeProfileButtonData(): PersistentList<Int> {
    val itemsList = persistentListOf(
        R.string.account,
        R.string.upload,
        R.string.basicSettings,
        R.string.languages,
        R.string.notification,
        R.string.privacyPolicy,
        R.string.support,
        R.string.about,
    )

    return itemsList
}
