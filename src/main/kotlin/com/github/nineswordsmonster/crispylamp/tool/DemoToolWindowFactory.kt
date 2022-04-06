package com.github.nineswordsmonster.crispylamp.tool

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import com.github.nineswordsmonster.crispylamp.MessageBundle
import com.github.nineswordsmonster.crispylamp.theme.WidgetTheme
import com.github.nineswordsmonster.crispylamp.widgets.Buttons
import com.github.nineswordsmonster.crispylamp.widgets.LazyScrollable
import com.github.nineswordsmonster.crispylamp.widgets.Loaders
import com.github.nineswordsmonster.crispylamp.widgets.TextInputs
import com.github.nineswordsmonster.crispylamp.widgets.Toggles
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory
import javax.swing.JComponent

private val LOG = logger<DemoToolWindowFactory>()

class DemoToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        LOG.info("someMethod() was called ${project.name}")

        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content = contentFactory.createContent(createCenterPanel(), MessageBundle.message("createTemplateConfig"), false)
        val content2 = contentFactory.createContent(createCenterPanel(), MessageBundle.message("archetype"), false)
        toolWindow.contentManager.addContent(content, 0)
        toolWindow.contentManager.addContent(content2, 0)
    }

    override fun isApplicable(project: Project): Boolean {
        return project.name.startsWith("un")
    }

    fun createCenterPanel(): JComponent {
        return ComposePanel().apply {
            setBounds(0, 0, 800, 600)
            setContent {
                WidgetTheme(darkTheme = true) {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        Row {
                            Column(
                                modifier = Modifier.fillMaxHeight().weight(1f)
                            ) {
                                Buttons()
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
