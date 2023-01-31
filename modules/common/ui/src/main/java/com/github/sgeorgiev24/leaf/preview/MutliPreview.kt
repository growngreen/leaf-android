package com.github.sgeorgiev24.leaf.preview

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Large font",
    group = "Large font group",
    fontScale = 1.5f,
    uiMode = UI_MODE_NIGHT_NO,
    showBackground = true
)
@Preview(
    name = "Large font",
    group = "Large font group",
    fontScale = 1.5f,
    uiMode = UI_MODE_NIGHT_YES,
    showBackground = true
)
annotation class FontScalePreview

@Preview(name = "Pixel XL", group = "Devices group", device = Devices.PIXEL_XL)
@Preview(name = "Pixel 2", group = "Devices group", device = Devices.PIXEL_2)
@Preview(name = "Pixel 3", group = "Devices group", device = Devices.PIXEL_3)
@Preview(name = "Pixel 4", group = "Devices group", device = Devices.PIXEL_4)
@Preview(name = "Pixel C", group = "Devices group", device = Devices.PIXEL_C)
annotation class DevicePreview