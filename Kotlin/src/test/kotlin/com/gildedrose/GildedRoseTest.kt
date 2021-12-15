package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import javax.print.attribute.standard.PrintQuality

internal class GildedRoseTest {

    @Test
    fun `given regular item with positive sellIn and quality, updateQuality should decrease quality and sellIn by 1`() {
        val items = arrayOf(Item("foo", 1, 7))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].sellIn)
        assertEquals(6, app.items[0].quality)
        assertEquals("foo", app.items[0].name)
    }

    @Test
    fun `given regular item with negative sellIn and positive quality, updateQuality should decrease quality by 2 and sellIn by 1`() {
        val items = arrayOf(Item("foo", -1, 7))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(-2, app.items[0].sellIn)
        assertEquals(5, app.items[0].quality)
        assertEquals("foo", app.items[0].name)
    }

    @Test
    fun `given regular item with sellIn 0 and positive quality, updateQuality should decrease quality by 2 and sellIn by 1`() {
        val items = arrayOf(Item("foo", 0, 7))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(-1, app.items[0].sellIn)
        assertEquals(5, app.items[0].quality)
        assertEquals("foo", app.items[0].name)
    }

    @Test
    fun `given regular item with positive sellIn and quality 0, updateQuality should keep quality at 0`() {
        val items = arrayOf(Item("foo", 1, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun `given regular item with negative sellIn and quality 0, updateQuality should keep quality at 0`() {
        val items = arrayOf(Item("foo", -1, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @Test
    fun `given regular item with negative sellIn and quality 1, updateQuality should set quality to 0`() {
        val items = arrayOf(Item("foo", -1, 1))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(0, app.items[0].quality)
    }

    @ParameterizedTest(name = "given aged brie with quality 10 and sellIn {0}, updateQuality should set quality to {1}")
    @CsvSource(
        "1,11",
        "0,12",
        "-1,12",
    )
    fun `given aged brie with quality 10, updateQuality should increase quality according to spec`(sellIn: Int, expectedQuality: Int) {
        val items = arrayOf(Item(ItemType.BRIE.itemName, sellIn, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(expectedQuality, app.items[0].quality)
    }

    @ParameterizedTest(name = "given aged brie with sellIn {0} and quality {1}, updateQuality should never increase quality above 50")
    @CsvSource(
        "1,50",
        "0,50",
        "-1,50",
        "-1,49",
        "0,49",
    )
    fun `given aged brie, updateQuality should never increase quality above 50`(sellIn: Int, quality: Int) {
        val items = arrayOf(Item(ItemType.BRIE.itemName, sellIn, quality))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(50, app.items[0].quality)
    }

    @ParameterizedTest(name = "given sulfuras with sellIn {0}, updateQuality should never change quality or sellIn")
    @ValueSource(ints = [1, 0, -1])
    fun `given sulfuras, updateQuality should never change quality or sellIn`(sellIn: Int) {
        val items = arrayOf(Item(ItemType.SULFURAS.itemName, sellIn, 80))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(80, app.items[0].quality)
        assertEquals(sellIn, app.items[0].sellIn)
    }

    @ParameterizedTest(name = "backstage passes with sellIn {0} and quality 10 should be set to quality {1} ")
    @CsvSource(
        "11,11",
        "10,12",
        "9,12",
        "6,12",
        "5,13",
        "1,13",
        "0,0",
        "-1,0",
    )
    fun `backstage passes increases and decreases in quality according to specs`(sellIn: Int, expectedQuality: Int) {
        val items = arrayOf(Item(ItemType.BACKSTAGE_PASSES.itemName, sellIn, 10))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(expectedQuality, app.items[0].quality)
    }

    @ParameterizedTest(name = "given conjured item with sellIn {0} and quality 7, should yield quality {1}")
    @CsvSource(
            "1,5",
            "0,3",
            "-1,3",
    )
    fun `given conjured item quality 7, should yield quality according to specs`(sellIn: Int, expectedQuality: Int) {
        val items = arrayOf(
            Item("Conjured Foo", sellIn, 7),
            Item("Conjured Bar", sellIn, 7),
        )
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(expectedQuality, app.items[0].quality)
        assertEquals(expectedQuality, app.items[1].quality)
    }
}


