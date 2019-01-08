import java.io.{File, FileInputStream}

import ircbot.{TestPircBotX, TestTwitchBotX}
import components.{CloseableMap, Context, RessourceManager}
import listeners.NdJsonWriteListener
import org.pircbotx.MultiBotManager
import org.pircbotx.hooks.ListenerAdapter
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

import scala.collection.immutable.HashMap


object Spasm {


  def main(args: Array[String]): Unit = {

    val filename = "/home/bigjerbd/Workspace/github/Spasm/etc/config.example.yml"
    val input = new FileInputStream(new File(filename))
    val yaml = new Yaml(new Constructor(classOf[SpasmConfig]))
    val e = yaml.load(input).asInstanceOf[SpasmConfig]
    println(e)

  }
}

