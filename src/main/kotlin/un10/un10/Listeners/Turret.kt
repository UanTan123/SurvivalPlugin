package un10.un10.Listeners

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import un10.un10.utils.loge
import un10.un10.utils.utils

class Turret : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        if (args.count() == 1 && sender is Player)
        {
            val p = sender
            when (args[0])
            {
                "create" ->
                {
                    utils.createTurret(p.location, "&cTurret", p)
                    p.sendMessage(loge.chatFormat("&a성공적으로 터렛을 생성하였습니다."))
                }
            }
        }

        return true
    }
}