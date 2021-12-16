package com.gildedrose.itemhandler

import com.gildedrose.Item
import com.gildedrose.ItemHandler
import com.gildedrose.ItemType

class SulfurasItem(item: Item) : ItemHandler(item) {

    override fun update() {
        // do nothing
    }

    override fun isHandlerResponsibleForItem(): Boolean {
        return item.name.equals(ItemType.SULFURAS.itemName)
    }
}
