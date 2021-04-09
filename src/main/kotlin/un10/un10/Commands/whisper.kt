package un10.un10.Commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import un10.un10.utils.loge
import java.lang.StringBuilder


class whisper : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        val w: Player = Bukkit.getServer().getPlayer(args[0]) as Player
        val str = StringBuilder()
        for (i in 1 until args.count())
        {
            str.append(args[i] + " ")
        }
        val msg = str.toString()
        w.sendMessage("&7${sender.name}로 부터 귓속말이 도착했습니다 : $msg")

        return true
    }
}
