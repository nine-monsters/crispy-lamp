package com.github.nineswordsmonster.crispylamp.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages


class MyAction: AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project: Project? = e.getData(PlatformDataKeys.PROJECT)
        val psiFile = e.getData(CommonDataKeys.PSI_FILE)
        val classPath = psiFile!!.virtualFile.path
        Messages.showMessageDialog(
            project,
            "guide-idea-plugin-create-project-by-gradle: $classPath", "Hi IDEA Plugin", Messages.getInformationIcon()
        )
    }
}
