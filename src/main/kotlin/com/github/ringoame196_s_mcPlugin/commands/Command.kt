package com.github.ringoame196_s_mcPlugin.commands

import com.github.ringoame196_s_mcPlugin.managers.InventoryManager
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.InventoryHolder

class Command : CommandExecutor {
    private val inventoryManager = InventoryManager()

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            val nonTargetMessage = "${ChatColor.RED}このコマンドはプレイヤーのみ実行可能です"
            sender.sendMessage(nonTargetMessage)
            return true
        }

        if (args.isEmpty()) return false
        val subCommand = args[0]

        when (subCommand) {
            CommandConst.BLOCK_COMMAND -> blockInventorySorting(sender)
            CommandConst.PLAYER_COMMAND -> playerInventorySorting(sender)
            else -> {
                val message = "${ChatColor.RED}構文が間違っています"
                sender.sendMessage(message)
            }
        }
        return true
    }

    private fun blockInventorySorting(player: Player) {
        val block = player.getTargetBlockExact(5)
        if (block != null && inventoryManager.isHaveInventory(block)) {
            val state = block.state as? InventoryHolder ?: return
            val inventory = state.inventory
            inventoryManager.sorting(inventory)
            sendEndMessage(player)
        } else {
            val message = "${ChatColor.RED}${block?.type ?: "air"}は 対象外のブロックです"
            player.sendMessage(message)
        }
    }

    private fun playerInventorySorting(player: Player) {
        val inventory = player.inventory
        inventoryManager.sorting(inventory)
        sendEndMessage(player)
    }

    private fun sendEndMessage(player: Player) {
        val message = "${ChatColor.GOLD}インベントリを整理しました"
        val sound = Sound.BLOCK_ANVIL_USE
        player.sendMessage(message)
        player.playSound(player, sound, 1f, 1f)
    }
}
