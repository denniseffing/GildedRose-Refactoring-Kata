package com.gildedrose

abstract class ItemHandler(protected val item: Item) {
    abstract fun update()
    abstract fun isHandlerResponsibleForItem(): Boolean

    fun getSellIn(): Int {
        return item.sellIn
    }

    fun getQuality(): Int {
        return item.quality
    }

    fun limitQuality(quality: Int): Int {
        return if (quality > 50) {
            50
        } else if (quality < 0) {
            0
        } else {
            quality
        }
    }
}
