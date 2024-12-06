package com.github.ringoame196_s_mcPlugin.managers

import org.bukkit.block.Block
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class InventoryManager {
    fun sorting(inventory: Inventory) {
        var inventoryFolder = mutableMapOf<ItemStack, Int>()
        val contents = inventory.contents.clone()

        // inventoryFolderにアイテムを追加する
        for (item in contents) {
            if (item == null) continue
            val amount = item.amount
            val keyItem = item.clone()
            keyItem.amount = 1
            inventoryFolder[keyItem] = (inventoryFolder[keyItem] ?: 0) + amount
        }

        // アイテムのID順に並び替える
        inventoryFolder = inventoryFolder.toSortedMap(compareBy { it.type })
        inventory.clear()

        // 整理した順にアイテムを設置
        for ((item, amount) in inventoryFolder) {
            val setItem = item.clone()
            setItem.amount = amount
            inventory.addItem(setItem)
        }
    }

    fun isHaveInventory(block: Block): Boolean {
        val state = block.state
        return state is InventoryHolder
    }
}
