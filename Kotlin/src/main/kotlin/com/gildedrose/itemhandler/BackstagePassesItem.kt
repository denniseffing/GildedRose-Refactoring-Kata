package com.gildedrose.itemhandler

import com.gildedrose.Item
import com.gildedrose.ItemHandler
import com.gildedrose.ItemType

class BackstagePassesItem(item: Item) : ItemHandler(item) {

    override fun update() {
        item.apply {
            if (item.sellIn > 10) {
                quality += 1
            } else if (sellIn > 5) {
                quality += 2
            } else if (sellIn > 0) {
                quality += 3
            } else {
                quality = 0
            }
            quality = limitQuality(quality)
            sellIn -= 1
        }
    }

    override fun isHandlerResponsibleForItem(): Boolean {
        return item.name.equals(ItemType.BACKSTAGE_PASSES.itemName)
    }
}
