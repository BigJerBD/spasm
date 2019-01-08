import ircbot.{TestPircBotX, TestTwitchBotX}
import components.{CloseableMap, Context, RessourceManager}
import listeners.NdJsonWriteListener
import org.pircbotx.MultiBotManager
import org.pircbotx.hooks.ListenerAdapter

import scala.collection.immutable.HashMap


object SpasmRun {

  def waitForInterrupt(): Unit = {
    wait(5000)
  }


  def main(args: Array[String]): Unit = {

    //read configurations and set ressources in a closable
    //start the bots
    //end base on a condition

    val rMan = new RessourceManager(
      "jsonListener" -> new NdJsonWriteListener("stub_path.json"),
    )

    Context.using(rMan) {
      rMan =>

        val bot = new TestTwitchBotX(rMan("jsonListener"))
        val manager: MultiBotManager = new MultiBotManager()
        manager.addBot(bot)

        bot.startBot()
        //manager.start()
        //manager.stop()

    }
  }
}

