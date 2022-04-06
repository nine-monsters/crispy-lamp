package com.github.nineswordsmonster.crispylamp.services

import com.github.nineswordsmonster.crispylamp.MessageBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MessageBundle.message("projectService", project.name))
    }
}
