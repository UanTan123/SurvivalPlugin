package un10.un10.Commands

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import un10.un10.utils.loge

class setWorlder : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        if (sender is Player)
        {
            val p = sender
            val wea = TextComponent("클릭시 날씨가 밝아 집니다.")
            wea.color = ChatColor.GOLD
            wea.isBold = true
            wea.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder("click here to be weather clear").color(ChatColor.GOLD).create())
            wea.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/weather clear")

            val ti = TextComponent("클릭시 시간이 0으로 바뀝니다.")
            ti.color = ChatColor.GOLD
            ti.isBold = true
            ti.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder("click here to be time set!").color(ChatColor.GOLD).create())
            ti.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/time set 0")

            sender.sendMessage(wea)
            sender.sendMessage(ti)
        }
        else
        {
            sender.sendMessage(loge.chatFormat("&cFuck you"))
        }

        return true
    }
}