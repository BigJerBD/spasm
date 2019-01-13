package runners

import org.pircbotx.{MultiBotManager, PircBotX}

class KeyboardStopRunner extends Runner {

  override def run(bots : MultiBotManager): Unit = {
    var curChar = ' '
    while (curChar != 'Q'){
      curChar = scala.io.StdIn.readChar()
    }
    bots.stop()
  }
}
