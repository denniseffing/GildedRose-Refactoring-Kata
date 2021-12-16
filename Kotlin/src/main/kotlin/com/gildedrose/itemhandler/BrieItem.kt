package com.gildedrose.itemhandler

import com.gildedrose.Item
import com.gildedrose.ItemHandler
import com.gildedrose.ItemType

class BrieItem(item: Item) : ItemHandler(item) {

    override fun update() {
        item.apply {
            quality = limitQuality(quality + if (sellIn > 0) 1 else 2)
            sellIn -= 1
        }
    }

    override fun isHandlerResponsibleForItem(): Boolean {
        return item.name.equals(ItemType.BRIE.itemName)
    }
}
