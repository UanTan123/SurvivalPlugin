package un10.un10.Commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import un10.un10.utils.loge

class WorldTelepoter : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        val world = Bukkit.getServer().getWorld(args[0])
        if (sender is Player)
        {
            sender.teleport(world!!.spawnLocation)
            sender.sendMessage(loge.chatFormat("&a성공적으로 월드 이동이 완료되었습니다!"))
        }

        return true
    }
}