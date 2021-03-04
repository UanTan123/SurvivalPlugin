package un10.un10.Listeners

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class SurvivalListeners : Listener
{
    fun chatFormat(msg: String): String
    {
        return ChatColor.translateAlternateColorCodes('&', msg)
    }

    @EventHandler
    fun onPlayerJoin(ev: PlayerJoinEvent)
    {
        val p = ev.player
        val online = Bukkit.getOnlinePlayers().size

        ev.joinMessage = chatFormat("&f&l[ &b&lJOIN &f&l] &e${p.name} 님이 서버에 접속하였습니다. &f&l[ &b&l동접자 : &e$online 명 &f&l]")
        if (!p.hasPlayedBefore())
        {
            ev.joinMessage = chatFormat("&f&l[ &b&lJOIN &f&l] &d${p.name} 님이 서버에 처음 접속하였습니다. &f&l[ &b&l동접자 : &e$online 명 &f&l]")
        }
        p.noDamageTicks = 0
    }

    @EventHandler
    fun onPlayerQuit(ev: PlayerQuitEvent)
    {
        val p = ev.player
        val online = Bukkit.getOnlinePlayers().size - 1
        ev.quitMessage = chatFormat("&f&l[ &c&lQUIT &f&l] &c${p.name} 님이 서버에서 퇴장하였습니다. &f&l[ &b&l동접자 : &e$online 명 &f&l]")
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
    }

}