package listeners

import org.pircbotx.hooks.ListenerAdapter
import org.pircbotx.hooks.types.GenericMessageEvent


class HelloWorldReplier extends ListenerAdapter {
  override def onGenericMessage(event: GenericMessageEvent): Unit = {
    if (event.getMessage.startsWith("?helloworld"))
      event.respond("Hello world!")
  }
}