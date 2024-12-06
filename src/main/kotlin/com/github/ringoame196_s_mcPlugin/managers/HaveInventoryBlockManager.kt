package com.github.ringoame196_s_mcPlugin.managers

import org.bukkit.block.Block
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

class HaveInventoryBlockManager {
    fun isTarget(block: Block): Boolean {
        val state = block.state
        return state is InventoryHolder
    }

    fun sorting(block: Block) {
        val haveInventoryBlock = block as? InventoryHolder ?: return
        val inventoryFolder = mapOf<ItemStack, Int>()
        val inventory = haveInventoryBlock.inventory
        val contents = inventory.contents.clone()
    }
}
