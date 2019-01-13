import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

import scala.collection.mutable.ListBuffer
import scala.beans.BeanProperty
import java.io.{File, FileInputStream}

import runners.Runner
import javax.management.MXBean
import metaprogramming.ObjectFactory
import org.pircbotx.Configuration.Builder
import org.pircbotx.{Configuration, PircBotX}
import org.pircbotx.hooks.ListenerAdapter


class SpasmConfig {
  @BeanProperty var listeners = Array.empty[ListenerConfig]
  @BeanProperty var bots = Array.empty[BotConfig]
  @BeanProperty var runner = new RunnerConfig()
}


class BotConfig {
  @BeanProperty var bottype = ""
  @BeanProperty var botname = ""
  @BeanProperty var username = ""
  @BeanProperty var server = ""
  @BeanProperty var password = ""
  @BeanProperty var channel = ""
  @BeanProperty var listeners = Array.empty[String]

  def makeInstance(listenerMap : Map[String,ListenerAdapter]): PircBotX ={
    val builder  = new Configuration.Builder()
    if (getServer != "") builder.addServer(getServer)
    if (getUsername != "") builder.setName(getUsername)
    if (getPassword != "") builder.setServerPassword(getPassword)
    if (getChannel != "") builder.addAutoJoinChannel(getChannel)
    for (listener:String <- listeners){
      listenerMap.get(listener) match {
        case Some(lstner) =>  builder.addListener(lstner)
        case _ => throw new NoSuchFieldException("listener does not exist")
      }
    }

    val botObject = Class.forName("ircbots." + bottype)
    val setupMethod = botObject.getMethod("setup",builder.getClass)

    setupMethod.invoke(botObject,builder)

    new PircBotX(builder.buildConfiguration())
  }
}


class ListenerConfig {
  @BeanProperty var name = ""
  @BeanProperty var classname = ""
  @BeanProperty var args = Array.empty[Object]

  def  makeInstance() : ListenerAdapter = {
    ObjectFactory.getObject(
      "listeners." + classname,args
    ).asInstanceOf[ListenerAdapter]
  }
}

class RunnerConfig extends  {
  @BeanProperty var classname = ""
  @BeanProperty var args = Array.empty[Object]

  def  makeInstance() : Runner = {
    ObjectFactory.getObject(
      "runners." + classname,args
    ).asInstanceOf[Runner]
  }
}

