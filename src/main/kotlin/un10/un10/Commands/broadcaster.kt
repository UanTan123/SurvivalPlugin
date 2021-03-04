package un10.un10.Commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.lang.StringBuilder
import org.checkerframework.checker.units.qual.s




class broadcaster : CommandExecutor
{
    fun chatFormat(msg: String): String
    {
        return ChatColor.translateAlternateColorCodes('&', msg)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        if (args.count() >= 1)
        {
            val str = StringBuilder()
            for (i in 0 until args.count())
            {
                str.append(args[i] + " ")
            }
            val msg = str.toString()
            Bukkit.getServer().broadcastMessage(chatFormat("&e&l[ &c공지 &e&l] &f$msg\n&a&l[ &6발신자 : &e${sender.name} &a&l]"))
        }
        else
        {
            sender.sendMessage("${ChatColor.RED}내용을 적어주세요.")
        }
        return true
    }
}