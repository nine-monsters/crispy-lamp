package com.github.nineswordsmonster.crispylamp.actions

import com.github.nineswordsmonster.crispylamp.apis.ApiClient
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ChickenSoupAction: AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project: Project? = e.getData(PlatformDataKeys.PROJECT)

        CoroutineScope(Dispatchers.IO).launch {

            val response = ApiClient.chickenSoupService.getPrice(32)

            // process response...

        }
    }
}
