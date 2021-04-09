package un10.un10.Commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import un10.un10.utils.loge
import java.lang.StringBuilder

class changeName : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        if (sender is Player)
        {
            val p = sender
            if (p.isOp)
            {
                if (args.count() == 1)
                {
                    val str = StringBuilder()
                    for (i in 1 until args.count())
                    {
                        str.append(args[i] + " ")
                    }
                    val msg = str.toString()
                    p.setDisplayName(loge.chatFormat(msg))
                }
                else if (args.count() == 2)
                {
                    val target = Bukkit.getServer().getPlayer(args[0]) as Player
                    val str = StringBuilder()
                    for (i in 2 until args.count())
                    {
                        str.append(args[i] + " ")
                    }
                    val msg = str.toString()
                    target.setDisplayName(loge.chatFormat(msg))
                }
            }
            else
            {
                if (args.count() == 1)
                {
                    val str = StringBuilder()
                    for (i in 1 until args.count())
                    {
                        str.append(args[i] + " ")
                    }
                    val msg = str.toString()
                    p.setDisplayName(loge.chatFormat(msg))
                }
            }
        }

        return true
    }
}