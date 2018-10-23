package ircbot

import org.pircbotx.hooks.ListenerAdapter
import org.pircbotx.hooks.types.GenericMessageEvent
import org.pircbotx.{Configuration, PircBotX}

class TestPircBotX(listener:ListenerAdapter) extends PircBotX(
  new Configuration.Builder()
    .setName("PircBotXUser")
    .addServer("irc.freenode.net")
    .addAutoJoinChannel("#pircbotx")
    .addListener(listener)
    .buildConfiguration()) {
}



