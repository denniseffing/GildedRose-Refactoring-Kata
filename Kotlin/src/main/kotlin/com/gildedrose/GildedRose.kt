package com.gildedrose

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items) {
            i.apply {
                if (name != ItemType.BRIE.itemName && name != ItemType.BACKSTAGE_PASSES.itemName) {
                    if (quality > 0) {
                        if (name != ItemType.SULFURAS.itemName) {
                            quality = quality - 1
                        }
                    }
                } else {
                    if (quality < 50) {
                        quality = quality + 1

                        if (name == ItemType.BACKSTAGE_PASSES.itemName) {
                            if (sellIn < 11) {
                                if (quality < 50) {
                                    quality = quality + 1
                                }
                            }

                            if (sellIn < 6) {
                                if (quality < 50) {
                                    quality = quality + 1
                                }
                            }
                        }
                    }
                }

                if (name != ItemType.SULFURAS.itemName) {
                    sellIn = sellIn - 1
                }

                if (sellIn < 0) {
                    if (name != ItemType.BRIE.itemName) {
                        if (name != ItemType.BACKSTAGE_PASSES.itemName) {
                            if (quality > 0) {
                                if (name != ItemType.SULFURAS.itemName) {
                                    quality = quality - 1
                                }
                            }
                        } else {
                            quality = quality - quality
                        }
                    } else {
                        if (quality < 50) {
                            quality = quality + 1
                        }
                    }
                }
            }
        }
    }

}

