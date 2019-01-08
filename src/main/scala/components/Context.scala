package components


import scala.collection.immutable.HashMap
import scala.language.reflectiveCalls
import scala.util.{Failure, Success, Try}

object Context {

  /**
    * using a closable element and close
    * it automatically while leaving context
    *
    * @param ressource openable ressource
    * @param proc  code block to execute with ressource
    * @tparam I closable type
    * @tparam O return type of block
    * @return block return type
    */
  def using [I <: AutoCloseable, O] (ressource: I) (proc: I => O) : O  = {
    Try(proc(ressource)) match {

      case Success(result) =>
        ressource.close()
        result

      case Failure(e) =>
        ressource.close()
        throw e

    }
  }
}
