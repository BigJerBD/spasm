package ircbot

import org.pircbotx.hooks.ListenerAdapter
import org.pircbotx.{Configuration, PircBotX}

class TestTwitchBotX(listener:ListenerAdapter) extends PircBotX(
  new Configuration.Builder()
    .setAutoNickChange(false) //Twitch doesn't support multiple users
    .setOnJoinWhoEnabled(false) //Twitch doesn't support WHO command

    .addServer("irc.twitch.tv")
    .setName("BigJerBD")
    .setServerPassword("<INSERT KEY>")
    .addAutoJoinChannel("#themunchdown")
    .addListener(listener)
    .buildConfiguration())
{


}



