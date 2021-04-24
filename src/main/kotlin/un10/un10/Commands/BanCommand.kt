package un10.un10.Commands

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import un10.un10.utils.loge

class BanCommand : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        if (sender is Player)
        {
            val p = sender
            val player_List = ArrayList<Player>(p.server.onlinePlayers)

            val bangui = Bukkit.createInventory(p, 45, loge.chatFormat("&9Player List"))
            for (i in 0 until player_List.size)
            {
                val playerHead = ItemStack(Material.PLAYER_HEAD, 1, 3)
                val meta = playerHead.itemMeta as SkullMeta
                meta.owningPlayer = player_List[i].player
                meta.setDisplayName(player_List[i].displayName)
                meta.lore = listOf(loge.chatFormat("&4Player Health : ${player_List[i].health}"), loge.chatFormat("&4Player XP : ${player_List[i].exp}"))
                playerHead.itemMeta = meta

                bangui.addItem(playerHead)
            }

            p.openInventory(bangui)
        }
        return true
    }
}