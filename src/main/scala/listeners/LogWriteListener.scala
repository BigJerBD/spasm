package listeners

import java.io.{BufferedWriter, File, FileWriter}
import java.util.{Calendar, Date}

import data.MsgInfo
import org.pircbotx.hooks.ListenerAdapter
import org.pircbotx.hooks.types.GenericMessageEvent

class LogWriteListener(path:String,flushTime:Integer)
  extends ListenerAdapter with AutoCloseable {

  def this(path:String){
    this(path,5000)
  }

  private var lastInputTime = Calendar.getInstance.getTime
  private val outputfile = new File(path)
  private val outputwrite = new BufferedWriter(new FileWriter(outputfile))

  override def onGenericMessage(event: GenericMessageEvent): Unit = {
    val writeTime = Calendar.getInstance.getTime
    val message = event.getMessage
    outputwrite.write(s"[$writeTime] $message\n")


    if (timeToFlush(writeTime)) {
      outputwrite.flush()
      lastInputTime = writeTime
    }
  }


  def close(): Unit = outputwrite.close()

  private def timeToFlush(writeTime: Date): Boolean =
    writeTime.getTime - lastInputTime.getTime > flushTime

}
