package un10.un10

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin
import un10.un10.Commands.*
import un10.un10.Listeners.SurvivalListeners


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
            loadConfig()
            getCommand("broadcaster")?.setExecutor(broadcaster())
            getCommand("math")?.setExecutor(Math())
            getCommand("gm")?.setExecutor(gameModeChange())
            getCommand("code")?.setExecutor(codeEnter())
            getCommand("whisper")?.setExecutor(whisper())
            getCommand("shutDown")?.setExecutor(shutDown())
            getCommand("setWorld")?.setExecutor(setWorlder())
            getCommand("cn")?.setExecutor(createNPCs())
            getCommand("sd")?.setExecutor(changeName())
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

    fun loadConfig()
    {
        getConfig().options().copyDefaults(true)
        saveConfig()
    }

}