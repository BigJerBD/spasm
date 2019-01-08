import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import scala.collection.mutable.ListBuffer
import scala.beans.BeanProperty
import java.io.{File, FileInputStream}


class SpasmConfig {
  @BeanProperty var listeners = new ListenerConfig
  @BeanProperty var bots = new BotConfig
  @BeanProperty var closerule = new CloseRuleConfig

}


class BotConfig {
  @BeanProperty var classname = ""
  @BeanProperty var username = ""
  @BeanProperty var server = ""
  @BeanProperty var channel = ""
  @BeanProperty var listeners = new java.util.ArrayList[String]
}


class ListenerConfig {
  @BeanProperty var classname = ""
  @BeanProperty var args = new java.util.ArrayList[String]


}

class CloseRuleConfig {
  @BeanProperty var classname = ""
  @BeanProperty var args = new java.util.ArrayList[String]
}

