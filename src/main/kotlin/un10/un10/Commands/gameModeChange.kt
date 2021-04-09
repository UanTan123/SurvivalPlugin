package un10.un10.Commands

import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import un10.un10.utils.loge

class gameModeChange : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        if (args.count() == 1 && sender is Player)
        {
            when (args[0])
            {
                "0" ->
                {
                    sender.gameMode = GameMode.SURVIVAL
                    sender.sendMessage(loge.chatFormat("&a성공적으로 게임모드가 변경되었습니다."))
                }
                "1" ->
                {
                    sender.gameMode = GameMode.CREATIVE
                    sender.sendMessage(loge.chatFormat("&a성공적으로 게임모드가 변경되었습니다."))
                }
                "2" ->
                {
                    sender.gameMode = GameMode.ADVENTURE
                    sender.sendMessage(loge.chatFormat("&a성공적으로 게임모드가 변경되었습니다."))
                }
                "3" ->
                {
                    sender.gameMode = GameMode.SPECTATOR
                    sender.sendMessage(loge.chatFormat("&a성공적으로 게임모드가 변경되었습니다."))
                }
                else ->
                {
                    sender.sendMessage(loge.chatFormat("&c0, 1, 2, 3중에서 입력해주세요!"))
                }
            }
        }
        else if (args.count() == 2 && sender is Player)
        {
            val p = Bukkit.getServer().getPlayer(args[1]) as Player
            when (args[0])
            {
                "0" ->
                {
                    p.gameMode = GameMode.SURVIVAL
                    sender.sendMessage(loge.chatFormat("&a성공적으로 게임모드가 변경되었습니다."))
                    p.sendMessage(loge.chatFormat("&a게임모드가 변경되었습니다."))
                }
                "1" ->
                {
                    p.gameMode = GameMode.CREATIVE
                    sender.sendMessage(loge.chatFormat("&a성공적으로 게임모드가 변경되었습니다."))
                    p.sendMessage(loge.chatFormat("&a게임모드가 변경되었습니다."))
                }
                "2" ->
                {
                    p.gameMode = GameMode.ADVENTURE
                    sender.sendMessage(loge.chatFormat("&a성공적으로 게임모드가 변경되었습니다."))
                    p.sendMessage(loge.chatFormat("&a게임모드가 변경되었습니다."))
                }
                "3" ->
                {
                    p.gameMode = GameMode.SPECTATOR
                    sender.sendMessage(loge.chatFormat("&a성공적으로 게임모드가 변경되었습니다."))
                    p.sendMessage(loge.chatFormat("&a게임모드가 변경되었습니다."))
                }
                else -> sender.sendMessage(loge.chatFormat("&c0, 1, 2, 3중에서 입력해주세요!"))
            }
        }
        else
        {
            if (sender !is Player && args.count() == 1)
            {
                sender.sendMessage(loge.chatFormat("&c당신은 플레이어가 아닙니다."))
            }
            else if (args.count() == 0)
            {
                sender.sendMessage(loge.chatFormat("&c0, 1, 2, 3중에서 입력해주세요!"))
            }
        }

        return true
    }
}