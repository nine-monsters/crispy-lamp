package com.github.nineswordsmonster.crispylamp.actions

import com.github.nineswordsmonster.crispylamp.services.OilPriceService
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.project.Project


private val LOG = logger<ChickenSoupAction>()

class ChickenSoupAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val project: Project? = e.getData(PlatformDataKeys.PROJECT)
        OilPriceService().getOil()
    }
}
