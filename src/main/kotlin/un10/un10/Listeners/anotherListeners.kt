package un10.un10.Listeners

import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Player
import org.bukkit.inventory.EquipmentSlot
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType


class anotherListeners : Listener
{

    @EventHandler
    fun onPlayerInteraction(ev: PlayerInteractEvent) {
        val p = ev.player
        val ploc = p.location
        val action = ev.action

        if (p.itemInHand.type == Material.GOLDEN_SWORD) {
            if (action == Action.RIGHT_CLICK_AIR) {
                p.setCooldown(Material.GOLDEN_SWORD, 1 * 20)
                val loc = Location(p.world, ploc.x + 0, ploc.y + 1, ploc.z + 1, p.location.yaw, p.location.pitch)
                val armorStand = p.world.spawn(loc, ArmorStand::class.java)
                /*armorStand.location.x + -1.5
                armorStand.location.y + 2.1
                armorStand.location.z + 1.5*/
                armorStand.isVisible = false
                armorStand.setItem(EquipmentSlot.HAND, ItemStack(Material.GOLDEN_SWORD))
                armorStand.setGravity(true)
                armorStand.isSmall = true
                armorStand.velocity = p.location.direction.multiply(5)
                p.itemInHand.amount -= 1
                for (e in armorStand.getNearbyEntities(0.5, 0.5, 0.5))
                {
                    if (armorStand.isOnGround)
                    {
                        armorStand.remove()
                    }
                    if (e is Player)
                    {
                        e.addPotionEffect(PotionEffect(PotionEffectType.POISON, 3 * 20, 3, true, false))
                        e.addPotionEffect(PotionEffect(PotionEffectType.SLOW, 5 * 20, 3, true, false))
                        armorStand.remove()
                    }
                }
            }
        }
    }
}