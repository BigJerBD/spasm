package runners
import org.pircbotx.{Configuration, MultiBotManager, PircBotX}

class TimedRunner(msTime:Integer) extends Runner {

  def run(bots : MultiBotManager): Unit = {
    wait(msTime.toInt)
    bots.stop()
  }
}
