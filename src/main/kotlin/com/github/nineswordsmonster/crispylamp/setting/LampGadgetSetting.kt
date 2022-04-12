package com.github.nineswordsmonster.crispylamp.setting

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.RadioButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.unit.dp
import com.github.nineswordsmonster.crispylamp.SettingBundle
import com.github.nineswordsmonster.crispylamp.entity.Location
import com.github.nineswordsmonster.crispylamp.services.OilPriceService
import com.github.nineswordsmonster.crispylamp.ui.theme.WidgetTheme
import com.github.nineswordsmonster.crispylamp.ui.theme.typography
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
import javax.swing.JToggleButton

val LOG = logger<LampGadgetSetting>()

class LampGadgetSetting : Configurable {
    private var locations: List<Location> = emptyList()

    init {
        locations = OilPriceService.instance.getLocations()
    }

    private val lampGadgetSettingsComponent: LampGadgetSettingsComponent = LampGadgetSettingsComponent(locations)

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

    override fun isModified(toggleButton: JToggleButton, value: Boolean): Boolean {
        return toggleButton.isSelected != value
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

class LampGadgetSettingsComponent(locations: List<Location>) {
    var selected: Int = LampGadgetSettingsState.instance.selected
    val component: JComponent = ComposePanel().apply {
        setContent {
            buildComponent(locations) {
                LOG.info("选择了 ${it}")
                selected = it
                LampGadgetSettingsState.instance.selected = it
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun buildComponent(locations: List<Location>, onChange: (Int) -> Unit) {
        var selected by remember { mutableStateOf(selected) }
        WidgetTheme(darkTheme = false) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Column {
                    Text(
                        text = SettingBundle.message("gadget.oil.setting.title"),
                        style = typography.h6,
                    )
                    Row(modifier = Modifier.fillMaxWidth()) {
                        val listState = rememberLazyListState()
                        LazyVerticalGrid(
                            cells = GridCells.Adaptive(100.dp),
                            modifier = Modifier.fillMaxSize(),
                            state = listState,
                            contentPadding = PaddingValues(4.dp)
                        ) {
                            items(locations.size) { index ->
                                val location = locations[index]
                                Row(modifier = Modifier.padding(6.dp)) {
                                    RadioButton(selected = selected == location.key,
//                                    modifier = Modifier.size(6.dp),
                                        onClick = {
                                            selected = location.key
                                            onChange(selected)
                                        }
                                    )
                                    Text(
                                        text = location.name,
                                        style = typography.body2,
                                        modifier = Modifier
                                            .align(Alignment.CenterVertically)
                                            .clickable(
                                                onClick = {
                                                    selected = location.key
                                                    onChange(selected)
                                                }
                                            )
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}
