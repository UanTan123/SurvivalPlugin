package un10.un10.utils

import com.google.gson.JsonParser
import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import net.minecraft.server.v1_16_R3.*
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.craftbukkit.v1_16_R3.CraftServer
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin.getPlugin
import un10.un10.Main
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.util.*
import java.util.ArrayList


object utils
{
    private val plugin: Plugin = getPlugin(Main::class.java)
    private val NPC: List<EntityPlayer> = ArrayList<EntityPlayer>()

    private fun getSkin(player: Player, name: String): Array<String>
    {
        try
        {
            val url = URL("https://api.mojang.com/users/profiles/minecraft/$name")
            val reader = InputStreamReader(url.openStream())
            val uuid = JsonParser().parse(reader).asJsonObject.get("id").asString
            val url2 = URL("https://sessionserver.mojang.com/session/minecraft/profile/${uuid}?unsigned=false")
            val reader2 = InputStreamReader(url2.openStream())
            val property = JsonParser().parse(reader2).asJsonObject.get("properties").asJsonArray.get(0).asJsonObject
            val texture = property.get("value").asString
            val signature = property.get("signature").asString
            return arrayOf(texture, signature)
            /*
            player :
                properties:
                    signature: none
                    texture: none

             */
        }
        catch (e: Exception)
        {
            val p = (player as CraftPlayer).handle
            val profile = p.profile
            val property = profile.properties.get("textures").iterator().next()
            val texture = property.value
            val signature = property.signature
            return arrayOf(texture, signature)
        }
    }

    fun broadcast(message: String)
    {
        Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', message))
    }

    fun ShutDownServer(message: String, count: Long, title: String, subTitle: String, fadeIn: Int, stay: Int, fadeOut: Int)
    {
        broadcast(loge.chatFormat("&e&l[ &c공지 &e&l] &f$message\nCount : $count\n&a&l[ &6발신자 : &eAutoServer Reload &a&l]"))
        Bukkit.getScheduler().runTaskLater(plugin, Runnable
        {
            val p: Player = Bukkit.getServer().onlinePlayers as Player
            p.sendTitle(title, subTitle, fadeIn, stay, fadeOut)
        }, count * 20)
        Bukkit.getScheduler().runTaskLater(plugin, Runnable { Bukkit.getServer().shutdown() }, 5 * 20)
    }

    fun createNPC(player: Player, skin: String, name: String)
    {
        val server = (Bukkit.getServer() as CraftServer).server
        val world = (Bukkit.getWorld(player.world.name) as CraftWorld).handle
        val gameProfile = GameProfile(UUID.fromString(player.uniqueId.toString()), name)
        val npc = EntityPlayer(server, world, gameProfile, PlayerInteractManager(world))
        npc.setLocation(player.location.x, player.location.y, player.location.z, player.location.yaw, player.location.pitch)
        addNPCPacket(npc)
        NPC.plus(npc)

        val name = getSkin(player, skin)
        gameProfile.properties.put("textures", Property("textures", name[0], name[1]))
    }

    fun addNPCPacket(npc: EntityPlayer)
    {
        for (player in Bukkit.getOnlinePlayers())
        {
            val connection = (player as CraftPlayer).handle.playerConnection
            connection.sendPacket(PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc))
            connection.sendPacket(PacketPlayOutNamedEntitySpawn(npc))
            connection.sendPacket(PacketPlayOutEntityHeadRotation(npc, (npc.yaw * 256 / 360).toByte()))
        }
    }

    fun addNPCJoinPacket(player: Player)
    {
        for (npc in NPC)
        {
            val connection = (player as CraftPlayer).handle.playerConnection
            connection.sendPacket(PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, npc))
            connection.sendPacket(PacketPlayOutNamedEntitySpawn(npc))
            connection.sendPacket(PacketPlayOutEntityHeadRotation(npc, (npc.yaw * 256 / 360).toByte()))
        }
    }

    fun getNPCs(): List<EntityPlayer>
    {
        return NPC
    }

    fun givePlayerHead(player: OfflinePlayer): org.bukkit.inventory.ItemStack
    {
        val item = ItemStack(Material.PLAYER_HEAD, 1, 3)
        val skull = item.itemMeta as SkullMeta
        skull.setDisplayName(loge.chatFormat(player.name.toString()))
        skull.lore = listOf("Custom Head")
        skull.owner = player.toString()
        item.itemMeta = skull
        return item
    }
}