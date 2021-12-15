package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items) {
            i.apply {
                when(name) {
                    ItemType.BRIE.itemName -> {
                        if (sellIn > 0) {
                            quality++
                        } else {
                            quality += 2
                        }
                        if (quality > 50) {
                            quality = 50
                        }
                    }
                    ItemType.BACKSTAGE_PASSES.itemName -> {
                        if (sellIn > 10) {
                            quality++
                        } else if (sellIn > 5) {
                            quality += 2
                        } else if (sellIn > 0) {
                            quality += 3
                        } else {
                            quality = 0
                        }
                        if (quality > 50) {
                            quality = 50
                        }
                    }
                    ItemType.SULFURAS.itemName -> {
                        // do nothing
                    }
                    else -> {
                        if (sellIn > 0) {
                            quality--
                        } else {
                            quality -= 2
                        }
                        if (quality < 0) {
                            quality = 0
                        }
                    }
                }
                sellIn = when(name) {
                    ItemType.SULFURAS.itemName -> sellIn
                    else -> sellIn - 1
                }
            }
        }
    }

}

