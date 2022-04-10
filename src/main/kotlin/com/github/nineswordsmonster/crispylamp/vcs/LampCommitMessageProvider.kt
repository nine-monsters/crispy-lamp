package com.github.nineswordsmonster.crispylamp.vcs

import com.intellij.openapi.project.Project
import com.intellij.openapi.vcs.changes.LocalChangeList
import com.intellij.openapi.vcs.changes.ui.CommitMessageProvider

class LampCommitMessageProvider: CommitMessageProvider {
    override fun getCommitMessage(forChangelist: LocalChangeList, project: Project): String? {
        return ""
    }
}
