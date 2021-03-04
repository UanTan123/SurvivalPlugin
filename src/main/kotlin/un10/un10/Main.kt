package un10.un10

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import un10.un10.Commands.broadcaster
import un10.un10.Listeners.SurvivalListeners
import un10.un10.Listeners.anotherListeners

class Main : JavaPlugin()
{
    var log = logger
    var config = description

    override fun onEnable()
    {
        try
        {
            log.info(chatFormat("&aPlugin successfully enabling..."))
            log.info(chatFormat("&bPlugin Version: 1.0v"))
            Bukkit.getServer().pluginManager.registerEvents(SurvivalListeners(), this)
            //Bukkit.getServer().pluginManager.registerEvents(anotherListeners(), this)
            getCommand("broadcaster")?.setExecutor(broadcaster())
        }
        catch (e: Exception)
        {
            log.info(chatFormat("&cPlugin don't work in this server\nError : $e"))
        }
    }

    override fun onDisable()
    {
        log.info(chatFormat("&cPlugin off."))
    }

    fun chatFormat(msg: String): String
    {
        return ChatColor.translateAlternateColorCodes('&', msg)
    }
}