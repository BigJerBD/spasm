package listeners

import java.io.{BufferedWriter, File, FileWriter}

import org.pircbotx.hooks.ListenerAdapter
import org.pircbotx.hooks.types.GenericMessageEvent
import java.text.SimpleDateFormat
import java.util.{Calendar, Date}

import com.google.gson.Gson
import data.MsgInfo

/**
  * Bot listener that write result in NdJson (usable by elastic search
  * @param path file path
  * @param flushTime time to flush data (affects performances)
  */
class NdJsonWriteListener(path: String, flushTime: Integer )
  extends ListenerAdapter with AutoCloseable {

  def this(path:String){
    this(path,5000)
  }

  private var lastInputTime = Calendar.getInstance.getTime
  private val outputfile = new File(path)
  private val outputwrite = new BufferedWriter(new FileWriter(outputfile))
  private val gson = new Gson

  override def onGenericMessage(event: GenericMessageEvent): Unit = {
    val writeTime = Calendar.getInstance.getTime
    val msgInfo = new MsgInfo(writeTime, event.getMessage)

    outputwrite.write(gson.toJson(msgInfo) + "\n")

    if (timeToFlush(writeTime)) {
      outputwrite.flush()
      lastInputTime = writeTime
    }

  }

  def close(): Unit = outputwrite.close()

  private def timeToFlush(writeTime: Date): Boolean =
    writeTime.getTime - lastInputTime.getTime > flushTime


}
