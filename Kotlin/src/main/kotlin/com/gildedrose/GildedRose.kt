package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items) {
            i.apply {
                quality = when (name) {
                    ItemType.BRIE.itemName -> limitQuality(
                            if (sellIn > 0) {
                                quality + 1
                            } else {
                                quality + 2
                            })
                    ItemType.BACKSTAGE_PASSES.itemName -> limitQuality(
                            if (sellIn > 10) {
                                quality + 1
                            } else if (sellIn > 5) {
                                quality + 2
                            } else if (sellIn > 0) {
                                quality + 3
                            } else {
                                0
                            })
                    ItemType.SULFURAS.itemName -> quality
                    else -> limitQuality(
                            if (sellIn > 0) {
                                quality - 1
                            } else {
                                quality - 2
                            })
                }

                sellIn = when (name) {
                    ItemType.SULFURAS.itemName -> sellIn
                    else -> sellIn - 1
                }
            }
        }
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

