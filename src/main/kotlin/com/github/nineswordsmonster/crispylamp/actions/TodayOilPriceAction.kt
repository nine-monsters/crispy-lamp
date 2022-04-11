package com.github.nineswordsmonster.crispylamp.actions

import com.github.nineswordsmonster.crispylamp.dialogs.OilPriceDialogWrapper
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.logger


private val LOG = logger<ChickenSoupAction>()

class ChickenSoupAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        OilPriceDialogWrapper(true).show()
    }
}
