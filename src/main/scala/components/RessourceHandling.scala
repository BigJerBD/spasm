package components


// note :: with a smart override of
// MapClass only close would be needed to implemented
class CloseableMap[T <: AutoCloseable](data: (String, T)*)
  extends AutoCloseable {
  var ressources: Map[String, T] = data.toMap

  def close(): Unit = for ((_, ressource) <- ressources) ressource.close()

  def apply(key: String): T = ressources(key)
}


class RessourceManager[T](ressources: (String, T)*)
  extends AutoCloseable {

  private val closeableTuple = ressources collect { case (s, v: AutoCloseable) => (s, v) }

  val closeableRessources = new CloseableMap(closeableTuple: _*)
  val allRessources = Map(ressources: _*)


  def apply(key: String): T = allRessources(key)

  override def close(): Unit = closeableRessources.close()
}



