package un10.un10.utils

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.entity.Player


object loge
{
    //Log
    fun info(msg: String)
    {
        println(chatFormat(msg))
    }

    fun chatFormat(msg: String): String
    {
        return ChatColor.translateAlternateColorCodes('&', msg)
    }

    fun toSend(p: Player, msg: String)
    {
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg))
    }

    fun toTitle(p: Player, title: String, sub: String, inFade: Int, stay: Int, outFade: Int)
    {
        p.sendTitle(title, sub, inFade, stay, outFade)
    }

    fun ps(p: Player, sounds: Sound, vol: Float, pit: Float)
    {
        p.playSound(p.location, sounds, vol, pit)
    }

    fun ps(p: String, sounds: Sound, vol: Float, pit: Float)
    {
        val player = Bukkit.getServer().getPlayer(p) as Player
        player.playSound(player.location, sounds, vol, pit)
    }

    fun ps(sounds: Sound, vol: Float, pit: Float)
    {
        for (p in Bukkit.getOnlinePlayers())
        {
            p.playSound(p.location, sounds, vol, pit)
        }
    }

    fun bc(text: String)
    {
        Bukkit.getServer().broadcastMessage(chatFormat(text))
    }
    //Log-End
}