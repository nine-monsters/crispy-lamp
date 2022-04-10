package com.github.nineswordsmonster.crispylamp.vcs

import com.intellij.dvcs.push.PrePushHandler
import com.intellij.dvcs.push.PushInfo
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.vcs.VcsCheckoutProcessor
import com.intellij.openapi.vfs.VirtualFile

val LOG = logger<CheckoutChecker>()
class CheckoutChecker: PrePushHandler {
    override fun getPresentableName(): String {
        return "CheckoutChecker"
    }

    override fun handle(pushDetails: MutableList<PushInfo>, indicator: ProgressIndicator): PrePushHandler.Result {
        LOG.info("aaa${pushDetails.toString()}")
        LOG.info("bbb${indicator.toString()}")
        return PrePushHandler.Result.ABORT_AND_CLOSE
    }
}
