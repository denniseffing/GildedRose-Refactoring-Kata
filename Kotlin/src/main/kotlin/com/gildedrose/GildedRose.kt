package com.gildedrose

import com.gildedrose.itemhandler.RegularItem
import org.reflections.Reflections

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        val reflections = Reflections("com.gildedrose")
        val itemHandlers = reflections.getSubTypesOf(ItemHandler::class.java)

        for (i in items) {

            val handler = itemHandlers
                    .stream()
                    .map { handler -> handler.getDeclaredConstructor(Item::class.java).newInstance(i) }
                    .filter { handler -> handler.isHandlerResponsibleForItem() }
                    .findFirst()
                    .orElse(RegularItem(i))

            handler.update()

            i.quality = handler.getQuality()
            i.sellIn = handler.getSellIn()
        }
    }
}
