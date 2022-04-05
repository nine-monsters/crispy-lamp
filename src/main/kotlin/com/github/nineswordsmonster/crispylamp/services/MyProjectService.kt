package com.github.nineswordsmonster.crispylamp.services

import com.intellij.openapi.project.Project
import com.github.nineswordsmonster.crispylamp.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
