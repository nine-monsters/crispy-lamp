package com.github.nineswordsmonster.crispylamp.listeners

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import com.github.nineswordsmonster.crispylamp.services.LampProjectService

internal class LampProjectManagerListener : ProjectManagerListener {

    override fun projectOpened(project: Project) {

        project.service<LampProjectService>()
    }
}
