import ircbot.TestPircBotX
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


    val rsrc = new RessourceManager(
      "jsonListener" -> new NdJsonWriteListener("stub_path.json"),
    )

    Context.using(rsrc) {
      rsrc =>

        val bot = new TestPircBotX(rsrc("jsonListener"))
        val manager: MultiBotManager = new MultiBotManager()
        manager.addBot(bot)

        //bot.startBot()
        manager.start()
        manager.stop()

    }
  }
}

