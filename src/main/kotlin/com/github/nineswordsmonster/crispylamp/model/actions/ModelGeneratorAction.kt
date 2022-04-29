package com.github.nineswordsmonster.crispylamp.model.actions

import com.intellij.database.psi.DbTable
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.LangDataKeys
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import java.util.stream.Collectors
import java.util.stream.Stream


val logger = logger<ModelGeneratorAction>()
class ModelGeneratorAction: AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project: Project? = e.project
        val tableElements: Array<PsiElement>? = e.getData(LangDataKeys.PSI_ELEMENT_ARRAY)
        if (tableElements == null) {
            logger.error("未选择表, 无法生成代码");
            return;
        }
        val dbTables: List<DbTable> = Stream.of(tableElements)
            .filter { t -> t is DbTable }
            .map { t -> t as DbTable }
            .collect(Collectors.toList())

    }


}