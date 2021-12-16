package com.gildedrose.itemhandler

import com.gildedrose.Item
import com.gildedrose.ItemHandler
import com.gildedrose.ItemType

class ConjuredItem(item: Item) : ItemHandler(item) {

    override fun update() {
        item.apply {
            quality = limitQuality(quality - if (sellIn > 0) 2 else 4)
            sellIn -= 1
        }
    }

    override fun isHandlerResponsibleForItem(): Boolean {
        return item.name.startsWith(ItemType.CONJURED.itemName)
    }
}
