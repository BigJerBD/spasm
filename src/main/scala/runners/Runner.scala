package runners

import org.pircbotx.{Configuration, MultiBotManager, PircBotX}

abstract class Runner {

  def run(botMan : MultiBotManager) : Unit
}
