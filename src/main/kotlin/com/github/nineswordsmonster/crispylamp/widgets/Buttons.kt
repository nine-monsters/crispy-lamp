package com.github.nineswordsmonster.crispylamp.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.nineswordsmonster.crispylamp.tool.DemoToolWindowFactory
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile

private val LOG = logger<DemoToolWindowFactory>()
@Composable
fun Buttons(project: Project?) {
    Row {
        val btnEnabled = remember { mutableStateOf(true) }
        val descriptor: FileChooserDescriptor =
            object : FileChooserDescriptor(true, true, false, true, true, true) {
            }
        Button(
            onClick = {
                FileChooser.chooseFile(descriptor, project, null) { f ->
                    LOG.info(f.name)
                }
            },
            modifier = Modifier.padding(8.dp),
            enabled = btnEnabled.value
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "FavoriteBorder",
                modifier = Modifier.padding(end = 4.dp)
            )
            Text(text = "Button")
        }
        val btnTextEnabled = remember { mutableStateOf(true) }
        TextButton(
            onClick = { btnTextEnabled.value = !btnTextEnabled.value },
            modifier = Modifier.padding(8.dp),
            enabled = btnTextEnabled.value
        ) {
            Text(text = "Text Button")
        }
        OutlinedButton(
            onClick = {
                btnEnabled.value = true
                btnTextEnabled.value = true
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = "Refresh",
                modifier = Modifier.padding(0.dp)
            )
        }
    }
}
