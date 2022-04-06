package com.github.nineswordsmonster.crispylamp.actions

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import com.github.nineswordsmonster.crispylamp.theme.WidgetTheme
import com.github.nineswordsmonster.crispylamp.widgets.*
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.DialogWrapper
import javax.swing.JComponent

class ComposeDemoAction : DumbAwareAction() {
    override fun actionPerformed(e: AnActionEvent) {
        DemoDialog(e.project).show()
    }

    class DemoDialog(project: Project?) : DialogWrapper(project) {
        init {
            title = "Demo"
            init()
        }

        override fun createCenterPanel(): JComponent {
            return ComposePanel().apply {
                setBounds(0, 0, 800, 600)
                setContent {
                    WidgetTheme(darkTheme = true) {
                        Surface(modifier = Modifier.fillMaxSize()) {
                            Row {
                                Column(
                                    modifier = Modifier.fillMaxHeight().weight(1f)
                                ) {
                                    Buttons(project = null)
                                    Loaders()
                                    TextInputs()
                                    Toggles()
                                }
                                Box(
                                    modifier = Modifier.fillMaxHeight().weight(1f)
                                ) {
                                    LazyScrollable()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
