package com.github.nineswordsmonster.crispylamp.dialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposePanel
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.github.nineswordsmonster.crispylamp.MessageBundle
import com.github.nineswordsmonster.crispylamp.entity.OilPrice
import com.github.nineswordsmonster.crispylamp.services.OilPriceService
import com.github.nineswordsmonster.crispylamp.setting.LampGadgetSettingsState
import com.github.nineswordsmonster.crispylamp.ui.theme.WidgetTheme
import com.intellij.openapi.ui.DialogWrapper
import javax.swing.JComponent

class OilPriceDialogWrapper(canBeParent: Boolean) : DialogWrapper(canBeParent) {
    init {
        title = MessageBundle.message("oil.dialog.title")
        init()
    }

    @Override
    override fun createCenterPanel(): JComponent {
        val price = OilPriceService.instance.getOil(LampGadgetSettingsState.instance.selected)
        return createCenterPanel(price)
    }
}

@OptIn(ExperimentalUnitApi::class)
fun createCenterPanel(price: OilPrice): JComponent {
    return ComposePanel().apply {
        setBounds(0, 0, 400, 400)
        setContent {
            WidgetTheme(darkTheme = false) {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Column {
                        Row(modifier = Modifier.padding(16.dp)) {
                            Column(
                                modifier = Modifier.wrapContentHeight()
                            ) {
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = price.location,
                                        modifier = Modifier.fillMaxWidth(),
                                        fontSize = TextUnit(1.5f, TextUnitType.Em),
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Row(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = price.startDate + "-" + price.endDate,
                                        modifier = Modifier.fillMaxWidth(),
                                        fontSize = TextUnit(.8f, TextUnitType.Em),
                                        textAlign = TextAlign.Right
                                    )
                                }
                                Divider(modifier = Modifier.height(2.dp))
                            }
                        }
                        Row(modifier = Modifier.padding(16.dp)) {
                            price.prices.forEach {
                                Column(
                                    modifier = Modifier.wrapContentHeight().width(200.dp)
                                ) {
                                    Text(it.key)
                                    val datas = it.value
                                    Divider(modifier = Modifier.height(1.dp))
                                    datas.forEach { price ->
                                        Row {
                                            Text(price.type, fontWeight = FontWeight.Bold, fontSize = TextUnit(1f, TextUnitType.Em))
                                            Spacer(modifier = Modifier.height(5.dp).width(5.dp))
                                            Text("¥ ", fontSize = TextUnit(.8f, TextUnitType.Em))
                                            Text(price.price, textAlign = TextAlign.Center, fontSize = TextUnit(1.2f, TextUnitType.Em))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
