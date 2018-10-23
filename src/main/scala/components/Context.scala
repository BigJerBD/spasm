package components


import scala.collection.immutable.HashMap
import scala.language.reflectiveCalls
import scala.util.{Failure, Success, Try}

object Context {

  def using[I <: {def close()}, O]
  (ressource: I)
  (block: I => O): O = {
    Try(block(ressource)) match {

      case Success(result) =>
        ressource.close()
        result

      case Failure(e) =>
        ressource.close()
        throw e

    }
  }
}
