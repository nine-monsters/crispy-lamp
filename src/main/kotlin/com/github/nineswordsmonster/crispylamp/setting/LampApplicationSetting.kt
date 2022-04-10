package com.github.nineswordsmonster.crispylamp.setting

import androidx.compose.ui.awt.ComposePanel
import com.github.nineswordsmonster.crispylamp.SettingBundle
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ConfigurableGroup
import javax.swing.JComponent


class LampApplicationSetting: ConfigurableGroup, Configurable {

    override fun getConfigurables(): Array<Configurable> {
        val list = mutableListOf<Configurable>().apply {
            add(LampGadgetSetting())
        }
        return list.toTypedArray()
    }

    override fun createComponent(): JComponent {
        return ComposePanel().apply {
            setContent {

            }
        }
    }

    override fun isModified(): Boolean {
        return false
    }

    override fun apply() {

    }

    override fun getDisplayName(): String {
        return SettingBundle.getMessage("lamp.setting")
    }
}
