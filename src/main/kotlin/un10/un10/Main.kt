package un10.un10

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin
import un10.un10.Commands.broadcaster
import un10.un10.Listeners.SurvivalListeners
import un10.un10.Commands.Math
import java.io.File

class Main : JavaPlugin()
{
    var log = logger
    var config = description

    override fun onEnable()
    {
        try
        {
            saveConfig()
            log.info(chatFormat("&aPlugin successfully enabling..."))
            log.info(chatFormat("&bPlugin Version: 1.0v"))
            Bukkit.getServer().pluginManager.registerEvents(SurvivalListeners(), this)
            //Bukkit.getServer().pluginManager.registerEvents(anotherListeners(), this)
            getCommand("broadcaster")?.setExecutor(broadcaster())
            getCommand("math")?.setExecutor(Math())
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


    fun SetConfig()
    {
        val file = File(dataFolder.toString() + File.separator + "config.yml")
        if (!file.exists())
        {
            getConfig().addDefault("Name", "Value")

            getConfig().options().copyDefaults(true)
            saveConfig()
        }
        else
        {
            CheckConfig()
            saveConfig()
            reloadConfig()
        }
    }

    fun CheckConfig()
    {
        if(getConfig().get("Name") == null)
        {
            getConfig().set("Name", "Value")
            saveConfig()
            reloadConfig()
        }
    }

}