package listeners

import java.io.{BufferedWriter, File, FileWriter}

import org.pircbotx.hooks.ListenerAdapter
import org.pircbotx.hooks.types.GenericMessageEvent
import java.text.SimpleDateFormat
import java.util.Calendar

import com.google.gson.Gson
import components.MsgInfo


class JsonWriteListener(path: String)

  extends ListenerAdapter with AutoCloseable{

  private val outputfile = new File(path)
  val outputwrite = new BufferedWriter(new FileWriter(outputfile))
  val gson = new Gson

  override def onGenericMessage(event: GenericMessageEvent): Unit = {
    val msgInfo = new MsgInfo(Calendar.getInstance.getTime, event.getMessage)

    outputwrite.write(gson.toJson(msgInfo))

  }

  def close(): Unit = {
    print("closing!")
    outputwrite.close()
  }
}
