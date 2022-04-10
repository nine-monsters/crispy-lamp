package com.github.nineswordsmonster.crispylamp.setting

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.options.ConfigurableProvider
import javax.swing.JComponent

class LampToolSetting : ConfigurableProvider() {

    override fun createConfigurable(): Configurable {
        return LampToolSettingConfigurable()
    }

    override fun canCreateConfigurable(): Boolean {
        return true
    }
}

class LampToolSettingConfigurable : Configurable {
    override fun createComponent(): JComponent? {
        TODO("Not yet implemented")
    }

    override fun isModified(): Boolean {
        TODO("Not yet implemented")
    }

    override fun apply() {
        TODO("Not yet implemented")
    }

    override fun getDisplayName(): String {
        TODO("Not yet implemented")
    }

}
