import java.io.{File, FileInputStream}

import ressourceUtil.{Context, RessourceManager}
import org.pircbotx.MultiBotManager
import org.pircbotx.hooks.ListenerAdapter
import metaprogramming.ObjectFactory
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import sun.reflect.generics.tree.BottomSignature


object Spasm {

  def getConfig(path: String): SpasmConfig = {
    val input = new FileInputStream(new File(path))
    val yaml = new Yaml(new Constructor(classOf[SpasmConfig]))

    yaml.load(input).asInstanceOf[SpasmConfig]
  }


  def main(args: Array[String]): Unit = {
    if (args.length != 1) throw new Exception("usage : please specify program arguments :　<config_path>")

    val config = getConfig(args(0))
    val listeners = for (lstConf <- config.getListeners)
      yield lstConf.getName -> lstConf.makeInstance
    val lsntMan = new RessourceManager(listeners: _*)
    val runner = config.getRunner.makeInstance()

    Context.using(lsntMan ) {
      lsntMan =>
        val botMan: MultiBotManager = new MultiBotManager()
        val bots = (
          for (botConf <- config.getBots)
            yield botConf.getBotname -> botConf.makeInstance(lsntMan.allRessources)
          ) toMap

        for (bot <- bots.values)  botMan.addBot(bot)
        botMan.start()
        runner.run(botMan)
    }

  }
}


