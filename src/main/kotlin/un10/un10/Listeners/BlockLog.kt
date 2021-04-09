package un10.un10.Listeners

import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin.getPlugin
import un10.un10.Main

class BlockLog : Listener
{
    private val plugin: Plugin = getPlugin(Main::class.java)
    private var count = 0

    @EventHandler
    fun onBreak(ev: BlockBreakEvent)
    {
        val block = ev.block
        val p = ev.player

        val loc = block.location

        if (block.type == Material.TNT)
        {
            plugin.config.set("Users." + p.uniqueId + ".Block_" + count + ".World", loc.world.name)
            plugin.config.set("Users." + p.uniqueId + ".Block_" + count + ".X", loc.blockX)
            plugin.config.set("Users." + p.uniqueId + ".Block_" + count + ".Y", loc.blockY)
            plugin.config.set("Users." + p.uniqueId + ".Block_" + count + ".Z", loc.blockZ)
            count++
            plugin.saveConfig()
        }
    }

    @EventHandler
    fun onBreak(ev: BlockPlaceEvent)
    {
        val block = ev.block
        val p = ev.player

        val loc = block.location

        if (block.type == Material.TNT)
        {
            plugin.config.set("Users." + p.uniqueId + ".Block_" + count + ".World", loc.world.name)
            plugin.config.set("Users." + p.uniqueId + ".Block_" + count + ".X", loc.blockX)
            plugin.config.set("Users." + p.uniqueId + ".Block_" + count + ".Y", loc.blockY)
            plugin.config.set("Users." + p.uniqueId + ".Block_" + count + ".Z", loc.blockZ)
            count++
            plugin.saveConfig()
        }
    }
}