package components

import java.text.SimpleDateFormat
import java.util.Date

object Configuration{
  val timeformat: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
}

class MsgInfo(time: String, message: String) {
  //todo :: verify if invalid format

  def this(time: Date, message: String) {
    this(Configuration.timeformat.format(time), message)
  }
}

case class OtherObject() {}
