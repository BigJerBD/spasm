package listeners

import org.pircbotx.hooks.ListenerAdapter
import org.pircbotx.hooks.types.GenericMessageEvent

class PrintListener extends ListenerAdapter{

  override def onGenericMessage(event: GenericMessageEvent): Unit = {
    println(event.getMessage)
  }
}


