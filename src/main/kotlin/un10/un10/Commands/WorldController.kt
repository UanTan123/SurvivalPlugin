package un10.un10.Commands

import org.bukkit.World
import org.bukkit.WorldCreator
import org.bukkit.WorldType
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import un10.un10.utils.loge

class WorldController : CommandExecutor
{

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        if (sender is Player)
        {
            val p = sender
            if (p.isOp && p.hasPermission("sp.control"))
            {
                val c = WorldCreator(args[0])
                when (args[1])
                {
                    "normal" -> c.type(WorldType.NORMAL)
                    "flat" -> c.type(WorldType.FLAT)
                    "large" -> c.type(WorldType.LARGE_BIOMES)
                    "amplified" -> c.type(WorldType.AMPLIFIED)
                    else -> c.type(WorldType.FLAT)
                }
                c.generateStructures(false)
                if (args.count() == 3)
                {
                    c.seed(args[2].toLong())
                }
                c.createWorld()
                sender.sendMessage(loge.chatFormat("&a성공적으로 월드 생성이 완료되었습니다\n&b월드 정보 :\n&bname : ${args[0]}\n&btype : ${args[1]}"))
            }
        }

        return true
    }
}