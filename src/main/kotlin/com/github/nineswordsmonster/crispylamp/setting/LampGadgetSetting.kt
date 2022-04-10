package com.github.nineswordsmonster.crispylamp.setting

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.unit.dp
import com.github.nineswordsmonster.crispylamp.SettingBundle
import com.github.nineswordsmonster.crispylamp.entity.Location
import com.github.nineswordsmonster.crispylamp.services.OilPriceService
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.options.Configurable
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import javax.swing.JComponent

val LOG = logger<LampGadgetSetting>()

class LampGadgetSetting : Configurable {
    private var locations: List<Location> = emptyList()

    init {
        locations = OilPriceService().getLocations()
    }

    private val lampGadgetSettingsComponent: LampGadgetSettingsComponent =
        LampGadgetSettingsComponent(locations[0], locations)

    override fun createComponent(): JComponent {
        return lampGadgetSettingsComponent.component
    }

    override fun isModified(): Boolean {
        val settings: LampGadgetSettingsState = LampGadgetSettingsState.instance
        return lampGadgetSettingsComponent.selected != settings.selected
    }

    override fun apply() {
        val lampGadgetSettingsState = LampGadgetSettingsState.instance
        lampGadgetSettingsState.selected = lampGadgetSettingsComponent.selected
    }

    override fun reset() {
        val settings: LampGadgetSettingsState = LampGadgetSettingsState.instance
        lampGadgetSettingsComponent.selected = settings.selected
    }

    override fun getDisplayName(): String {
        return SettingBundle.getMessage("gadget.setting")
    }
}

@State(
    name = "com.github.nineswordsmonster.crispylamp.setting.LampGadgetSettingsState",
    storages = [Storage("LampSettingsPlugin.xml")]
)
class LampGadgetSettingsState : PersistentStateComponent<LampGadgetSettingsState> {
    var selected = 32

    @Nullable
    override fun getState(): LampGadgetSettingsState {
        return this
    }

    override fun loadState(@NotNull state: LampGadgetSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        val instance: LampGadgetSettingsState
            get() = ApplicationManager.getApplication().getService(LampGadgetSettingsState::class.java)
    }
}

class LampGadgetSettingsComponent(location: Location, locations: List<Location>) {
    var selected: Int = location.key

    val component: JComponent = ComposePanel().apply {
        setContent {
            buildComponent(locations) {
                LOG.info("选择了 ${it}")
                selected = it
            }
        }
    }

    @Composable
    private fun buildComponent(locations: List<Location>, onChange: (Int) -> Unit) {
        var selected by remember { mutableStateOf(32) }
        MaterialTheme {
            Row {
                locations.forEach {
                    Column(modifier = Modifier.padding(12.dp)) {
                        RadioButton(selected = selected == it.key,
                            modifier = Modifier.size(6.dp),
                            onClick = {
                                selected = it.key
                                onChange(selected)
                            }
                        )
                        Text(
                            text = it.name,
                            modifier = Modifier.clickable(
                                onClick = {
                                    selected = it.key
                                    onChange(selected)
                                }
                            ).padding(start = 4.dp)
                        )
                    }
                }
            }
        }
    }
}
