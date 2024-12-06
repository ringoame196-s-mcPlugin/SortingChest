package com.github.ringoame196_s_mcPlugin.commands

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.Listener

class Command:CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            val nonTargetMessage = "${ChatColor.RED}このコマンドはプレイヤーのみ実行可能です"
            sender.sendMessage(nonTargetMessage)
            return true
        }
        val block = sender.getTargetBlockExact(5)
        if (block == null || block.type != Material.CHEST) {
            val message = "${ChatColor.RED}${block?.type?:"air"}は 対象外のブロックです"
            sender.sendMessage(message)
            return true
        }
        return true
    }
}
