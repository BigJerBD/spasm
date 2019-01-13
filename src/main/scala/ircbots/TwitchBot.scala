package ircbots

import org.pircbotx.Configuration.Builder

object TwitchBot {

  def setup(botBuilder:Builder): Unit = {
    botBuilder
      .setAutoNickChange(false) //Twitch doesn't support multiple users
      .setOnJoinWhoEnabled(false) //Twitch doesn't support WHO command
  }
}
