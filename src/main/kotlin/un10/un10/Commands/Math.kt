package un10.un10.Commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import un10.un10.utils.loge
import kotlin.math.pow

class Math : CommandExecutor
{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        val one: Double = args[0].toDouble()
        val two: Double = args[2].toDouble()
        val result: Int

        when(args[1])
        {
            "+" ->
            {
                result = (one + two).toInt()
                sender.sendMessage(loge.chatFormat("&a결과 값 : $result"))
            }
            "-" ->
            {
                result = (one - two).toInt()
                sender.sendMessage(loge.chatFormat("&a결과 값 : $result"))
            }
            "*" ->
            {
                result = (one * two).toInt()
                sender.sendMessage(loge.chatFormat("&a결과 값 : $result"))
            }
            "/" ->
            {
                result = (one / two).toInt()
                sender.sendMessage(loge.chatFormat("&a결과 값 : $result"))
            }
            "^" ->
            {
                result = one.pow(two).toInt()
                sender.sendMessage(loge.chatFormat("&a결과 값 : $result"))
            }
            else ->
            {
                sender.sendMessage(loge.chatFormat("&c결과 값 : ERROR."))
            }
        }

        return true
    }
}