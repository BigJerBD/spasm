import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import scala.collection.mutable.ListBuffer
import scala.beans.BeanProperty
import java.io.{File, FileInputStream}


class SpasmConfig {
  @BeanProperty var listeners = Array.empty[ListenerConfig]
  @BeanProperty var bots = Array.empty[BotConfig]
  @BeanProperty var closers = Array.empty[CloseRuleConfig]
}


class BotConfig {
  @BeanProperty var classname = ""
  @BeanProperty var username = ""
  @BeanProperty var server = ""
  @BeanProperty var channel = ""
  @BeanProperty var listeners = Array.empty[String]
}


class ListenerConfig {
  @BeanProperty var classname = ""
  @BeanProperty var args = Array.empty[String]
}

class CloseRuleConfig {
  @BeanProperty var classname = ""
  @BeanProperty var args = Array.empty[String]
}

