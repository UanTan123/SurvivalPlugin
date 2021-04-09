package un10.un10.Commands

import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import un10.un10.utils.loge

class codeEnter : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        if (sender is Player)
        {
            if (args[0] == "CqTycH982")
            {
                sender.sendMessage(loge.chatFormat("&aCode entered."))
                sender.inventory.addItem(ItemStack(Material.DIAMOND, 6))
                sender.updateInventory()
            }
            else if (args[0] == "HclCaCo3CaCl2Co2H2O")
            {
                sender.sendMessage(loge.chatFormat("&a와 ㅅㅂ 이걸 마추네 ㄹㅇ"))
                sender.inventory.addItem(ItemStack(Material.LAVA_BUCKET, 2))
                sender.inventory.addItem(ItemStack(Material.BONE, 1))
                sender.inventory.addItem(ItemStack(Material.BONE_MEAL, 1))
                sender.inventory.addItem(ItemStack(Material.GLASS_BOTTLE, 1))
                sender.inventory.addItem(ItemStack(Material.WATER_BUCKET, 1))
                sender.updateInventory()
            }
        }

        return true
    }
}