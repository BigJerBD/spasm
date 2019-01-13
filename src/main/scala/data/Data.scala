package data

import java.text.SimpleDateFormat
import java.util.Date

object Configuration{
  val timeformat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
}

case class MsgInfo(time: String, message: String) {

  def this(time: Date, message: String) {
    this(Configuration.timeformat.format(time), message)
  }

}

