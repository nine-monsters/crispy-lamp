package com.github.nineswordsmonster.crispylamp.tool

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class DemoToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val viewPanel = SimpleToolWindowPanel(false)
        //获取内容工厂实例
        val contentFactory = ContentFactory.SERVICE.getInstance()
//内容工厂创建内容
//因为第一个参数要一个JPanel所以GUI内部定义方法将最外层JPanel返回
        val content = contentFactory.createContent(viewPanel, "Demo", false)
//设置内容
        toolWindow.contentManager.addContent(content, 0)
    }
}
