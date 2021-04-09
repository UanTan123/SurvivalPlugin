package un10.un10.Commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import un10.un10.utils.loge
import un10.un10.utils.utils
import java.lang.StringBuilder

class shutDown : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        val str = StringBuilder()
        for (i in 1 until args.count())
        {
            str.append(args[i] + " ")
        }
        val msg = str.toString()
        utils.ShutDownServer(loge.chatFormat(msg),
            args[0].toLong(), loge.chatFormat("&cServer ShutDown"),
            loge.chatFormat("&cIn ${args[0]} seconds"),
            0, 100, 0
        )

        return true
    }
}