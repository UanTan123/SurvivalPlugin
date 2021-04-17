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
        val hash = HashMap<Player, Boolean>()
        if (sender is Player)
        {
            if (hash.containsKey(sender))
            {
                if (args[0] == "CqTycH982")
                {
                    sender.sendMessage(loge.chatFormat("&aCode entered."))
                    sender.inventory.addItem(ItemStack(Material.DIAMOND, 6))
                    sender.updateInventory()
                    hash[sender] = true
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
                    hash[sender] = true
                }
                else if (args[0] == "Hjq1cRnlI92")
                {
                    sender.sendMessage(loge.chatFormat("&a초보자 세트 코드 입력완료 되었습니다."))
                    sender.inventory.addItem(ItemStack(Material.TOTEM_OF_UNDYING, 1))
                    sender.inventory.addItem(ItemStack(Material.STONE_SWORD, 1))
                    sender.inventory.addItem(ItemStack(Material.STONE_AXE, 1))
                    sender.inventory.addItem(ItemStack(Material.STONE_PICKAXE, 1))
                    sender.inventory.addItem(ItemStack(Material.STONE_HOE, 1))
                    sender.inventory.addItem(ItemStack(Material.STONE_SHOVEL, 1))
                    sender.updateInventory()
                    hash[sender] = true
                }
                else
                {
                    sender.sendMessage(loge.chatFormat("&c코드가 유효하지 않습니다."))
                }
            }
            else
            {
                sender.sendMessage(loge.chatFormat("&c당신은 이미 한가지의 아이템을 지급 받았습니다."))
            }
        }

        return true
    }
}