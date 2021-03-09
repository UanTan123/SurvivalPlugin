package un10.un10.function

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot

import org.bukkit.scoreboard.Team
import un10.un10.logger.loge

object sb
{
    var manager = Bukkit.getScoreboardManager()
    var board = manager.newScoreboard
    var HI = board.registerNewTeam("HUMAN")
    var ZB = board.registerNewTeam("ZOMBIE")

    //Team
    fun TeamCreate(name: String)
    {
        board.registerNewTeam(name)
    }

    fun TeamAdd(p: Player, team: String)
    {
        val name = team as Team
        name.addPlayer(p)
    }

    fun TeamRemove(p: Player, team: String)
    {
        val name = team as Team
        name.removePlayer(p)
    }

    fun TeamPrefix(team: String, prefix: String)
    {
        val name = team as Team
        name.prefix = loge.chatFormat(prefix)
    }

    fun TeamSuffix(team: String, suffix: String)
    {
        val name = team as Team
        name.suffix = loge.chatFormat(suffix)
    }

    fun TeamSetInvisibles(team: String, isInvisibles: Boolean)
    {
        val name = team as Team
        name.setCanSeeFriendlyInvisibles(isInvisibles)
    }

    fun TeamSetFriendlyFire(team: String, isFriendlyFire: Boolean)
    {
        val name = team as Team
        name.setAllowFriendlyFire(isFriendlyFire)
    }

    fun TeamSetColor(team: String, color: ChatColor)
    {
        val name = team as Team
        name.color = color
    }
    //Team

    //ScoreBoard
    fun SbCreate(name: String, type: String, displaySlot: DisplaySlot, displayName: String)
    {
        val objective = board.registerNewObjective(name, type)
        objective.displaySlot = displaySlot
        objective.displayName = displayName
        /*val score = objective.getScore(ChatColor.GREEN.toString() + "TEST : ")
        score.score = 1*/

        fun SBsetScore(scoreName: String, Score: Int)
        {
            val score = objective.getScore(loge.chatFormat(scoreName))
            score.score = Score
        }
    }
}