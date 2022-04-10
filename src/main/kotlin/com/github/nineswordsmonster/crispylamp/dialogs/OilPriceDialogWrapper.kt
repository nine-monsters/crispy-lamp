package com.github.nineswordsmonster.crispylamp.dialogs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import com.github.nineswordsmonster.crispylamp.entity.OilPrice
import com.github.nineswordsmonster.crispylamp.services.OilPriceService
import com.github.nineswordsmonster.crispylamp.ui.theme.WidgetTheme
import com.intellij.openapi.ui.DialogWrapper
import javax.swing.JComponent

class OilPriceDialogWrapper(canBeParent: Boolean) : DialogWrapper(canBeParent) {
    init {
        title = "OilPriceDialogWrapper"
        init()
    }

    @Override
    override fun createCenterPanel(): JComponent {
        val price = OilPriceService().getOil()
        return createCenterPanel(price)
    }
}

fun createCenterPanel(price: OilPrice): JComponent {
    return ComposePanel().apply {
        setBounds(0, 0, 400, 400)
        setContent {
            WidgetTheme(darkTheme = false) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Row {
                        Text(text = price.location)
                        Text(text = price.startDate + "-" + price.endDate)
                        price.prices.forEach {
                            Text(text = it.key)
                            val datas = it.value
                            datas.forEach { data ->
                                Text(data.type)
                                Text(data.price)
                            }
                        }
                    }
                }
            }
        }
    }
}
