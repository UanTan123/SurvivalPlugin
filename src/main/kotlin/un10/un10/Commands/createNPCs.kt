package un10.un10.Commands

import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.NPC
import org.bukkit.entity.Player
import un10.un10.utils.loge
import un10.un10.utils.utils
import java.lang.StringBuilder

class createNPCs : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        if (sender is Player)
        {
            val p = sender
            val target: Player = Bukkit.getPlayer(args[0]) as Player
            val str = StringBuilder()
            for (i in 2 until args.count())
            {
                str.append(args[i] + " ")
            }
            val msg = str.toString()
            utils.createNPC(sender, target.toString(), msg)
            sender.sendMessage(loge.chatFormat("&aSuccessfully created npc.\n&aName : $msg\n&aSkin : $target"))
        }

        return true
    }
}