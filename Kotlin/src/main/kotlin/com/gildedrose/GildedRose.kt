package com.gildedrose


fun limitQuality(quality: Int): Int {
    return if (quality > 50) {
        50
    } else if (quality < 0) {
        0
    } else {
        quality
    }
}


fun calculateBrieItemQuality(item: Item) = limitQuality(
    if (item.sellIn > 0) {
        item.quality + 1    } else {
        item.quality + 2
    }
)

fun calculateBackstagePassesItemQuality(item: Item) = limitQuality(
    if (item.sellIn > 10) {
        item.quality + 1
    } else if (item.sellIn > 5) {
        item.quality + 2
    } else if (item.sellIn > 0) {
        item.quality + 3
    } else {
        0
    }
)

fun calculateSulfurasItemQuality(item: Item) = item.quality

fun calculateConjuredItemQuality(item: Item) = limitQuality(
    if (item.sellIn > 0) {
        item.quality - 2
    } else {
        item.quality - 4
    }
)

fun calculateRegularItemQuality(item: Item) = limitQuality(
    if (item.sellIn > 0) {
        item.quality - 1
    } else {
        item.quality - 2
    }
)

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items) {
            i.apply {
                quality = when {
                    name == ItemType.BRIE.itemName -> calculateBrieItemQuality(this)
                    name == ItemType.BACKSTAGE_PASSES.itemName -> calculateBackstagePassesItemQuality(this)
                    name == ItemType.SULFURAS.itemName -> calculateSulfurasItemQuality(this)
                    name.startsWith(ItemType.CONJURED.itemName) -> calculateConjuredItemQuality(this)
                    else -> calculateRegularItemQuality(this)
                }

                sellIn = when (name) {
                    ItemType.SULFURAS.itemName -> sellIn
                    else -> sellIn - 1
                }
            }
        }
    }


}

