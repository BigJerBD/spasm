import ircbot.TestPircBotX
import listeners.JsonWriteListener
import org.pircbotx.MultiBotManager

object PircBotxHelloWorld {

  def autoclose[T <: {def close()}]
  (resource: T)
  (block: T => Unit) {
    try {
      block(resource)
    } finally {
      if (resource != null) resource.close()
    }
  }

  def main(args: Array[String]): Unit = {

    //todo create a special manager for that allow to specify the ressource to allocate
    //todo and having a way to interface it closing (timer, key?)

    autoclose(new JsonWriteListener("stub_path.json"))
    {
      listener =>
        val bot = new TestPircBotX(listener)
        val manager : MultiBotManager = new MultiBotManager()
        manager.addBot(bot)
        manager.start()
    }

  }
}
