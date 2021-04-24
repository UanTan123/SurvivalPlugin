package un10.un10.Listeners

import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerChatEvent
import org.bukkit.event.player.PlayerCommandSendEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.server.ServerListPingEvent
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin.getPlugin
import org.bukkit.util.Vector
import un10.un10.Main
import un10.un10.utils.loge
import un10.un10.utils.utils


class SurvivalListeners() : Listener
{

    fun chatFormat(msg: String): String
    {
        return ChatColor.translateAlternateColorCodes('&', msg)
    }

    private val plugin: Plugin = getPlugin(Main::class.java)

    @EventHandler
    fun onServerPing(ev: ServerListPingEvent)
    {
        var motd = "&e&l========== UXG ==========\n&b&l자작 플러그인 적용"
        //var motd = "bcb3b51f-5f8c-4ed8-979b-385d64f4b8e8"
        motd = motd.replace('&', '\u00A7')
        ev.motd = motd
    }

    @EventHandler
    fun onPlayerJoin(ev: PlayerJoinEvent)
    {
        val p = ev.player
        val online = Bukkit.getOnlinePlayers().size

        ev.joinMessage = chatFormat("&f&l[ &b&lJOIN &f&l] &e${p.name} 님이 서버에 접속하였습니다. &f&l[ &b&l동접자 : &e$online 명 &f&l]")
        p.playSound(p.location, Sound.ENTITY_PLAYER_LEVELUP, SoundCategory.MASTER, 100.0f, 1.0f)
        loge.info("&aPlayer Join :\n&aName : ${p.name}\n&aOnlinePlayer : $online")
        if (!p.hasPlayedBefore())
        {
            ev.joinMessage = chatFormat("&f&l[ &b&lJOIN &f&l] &d${p.name} 님이 서버에 처음 접속하였습니다. &f&l[ &b&l동접자 : &e$online 명 &f&l]")
            loge.info("&dPlayer First Join :\nName : ${p.name}\nOnlinePlayer : $online")
            p.teleport(Bukkit.getServer().getWorld("lobby")!!.spawnLocation)
            p.playSound(p.location, Sound.ENTITY_PLAYER_LEVELUP, SoundCategory.MASTER, 100.0f, 1.0f)
        }
        p.noDamageTicks = 0

        if (utils.getNPCs() == null)
        {
            return
        }
        if (utils.getNPCs().isEmpty())
        {
            return
        }
        utils.addNPCJoinPacket(ev.player)
    }

    @EventHandler
    fun onPlayerQuit(ev: PlayerQuitEvent)
    {
        val p = ev.player
        val online = Bukkit.getOnlinePlayers().size - 1
        ev.quitMessage = chatFormat("&f&l[ &c&lQUIT &f&l] &c${p.name} 님이 서버에서 퇴장하였습니다. &f&l[ &b&l동접자 : &e$online 명 &f&l]")
        loge.info("&cPlayer Left :\n&cName : ${p.name}\n&cOnlinePlayer : $online")
    }

    @EventHandler
    fun onDamageChecker(ev: EntityDamageByEntityEvent)
    {
        val victim = ev.entity
        val attacker = ev.damager
        val cause = ev.cause

        if (attacker is Player && victim is Player)
        {
            victim.sendMessage(chatFormat("&4[ &cDAMAGE CHECKER &4] &c당신은 ${attacker.name} 님에게 $cause 를 사용하여 ${ev.damage} 만큼의 데미지를 입었습니다."))
            attacker.sendMessage(chatFormat("&4[ &cDAMAGE CHECKER &4] &c당신은 $cause 를 사용하여 ${victim.name} 님에게 ${ev.damage} 만큼의 데미지를 입혔습니다."))
        }
    }

    @EventHandler
    fun onPlayerDeath(ev: PlayerDeathEvent)
    {
        val p = ev.entity as Player
        ev.deathMessage = chatFormat("&c사람이 죽었습니다.")
        p.sendMessage(chatFormat("&c당신은 사망하였습니다."))
        loge.info("&cPlayer Death.\nName : ${p.name}")
        p.world.dropItem(p.location, utils.givePlayerHead(p))
    }

    @EventHandler
    fun onExplode(ev: EntityExplodeEvent)
    {
        for (b in ev.blockList())
        {
            if (b.type == Material.TNT) return
            val x = -0.5f + (Math.random() * (0.5f - -0.5f + 1)).toFloat()
            val y = -1.5f + (Math.random() * (0.5f - -0.5f + 1)).toFloat()
            val z = -0.5f + (Math.random() * (0.5f - -0.5f + 1)).toFloat()
            val fallingBlock = b.world.spawnFallingBlock(b.location, b.type, b.data)
            fallingBlock.dropItem = false
            fallingBlock.velocity = Vector(x, y, z)
            b.type = Material.AIR
        }
    }

    @EventHandler
    fun onChattingEvent(ev: PlayerChatEvent)
    {
        val p = ev.player
        if (p.isOp)
        {
            ev.format = loge.chatFormat("&e&l[&c&lOP&e&l] &b${p.name} &f: ${loge.chatFormat(ev.message)}")
        }
        else if (p.hasPermission("sp.chatting"))
        {
            ev.format = loge.chatFormat("&a&l[&b&l후원자&a&l] &f${p.name} : ${loge.chatFormat(ev.message)}")
        }
        else
        {
            ev.isCancelled = true
            p.sendMessage(loge.chatFormat("&c채팅이 허용되지 않습니다!"))
        }
    }

}