package com.sidescreen.app

import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ResolutionHintTest {
    private val panel = PanelGeometry(2560, 1600, 60)

    @Test
    fun matchingShapeGivesNoHint() {
        assertNull(panel.resolutionHint(2560, 1600))
        assertNull(panel.resolutionHint(1280, 800))
        assertNull(panel.resolutionHint(1600, 2560)) // rotated
        assertNull(panel.resolutionHint(1264, 784)) // decoder-capped, floored to 16
    }

    @Test
    fun mismatchedShapeSuggestsNativeAndHiDpi() {
        val hint = panel.resolutionHint(1920, 1080)!!
        assertTrue(hint.contains("2560x1600"))
        assertTrue(hint.contains("1280x800 with HiDPI"))
    }
}
