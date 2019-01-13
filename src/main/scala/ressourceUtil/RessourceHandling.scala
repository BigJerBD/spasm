package ressourceUtil

/**
  * A ressource Mapping that can close all his element
  *
  * @note with a smart override of MapClass only close would be needed to implemented
  * @param data variable amount of closableData with a name
  */
class CloseableMap [T <: AutoCloseable] (data: (String, T)*)
  extends AutoCloseable {

  var ressources: Map[String, T] = data.toMap

  //methods
  def close(): Unit = for ((_, ressource) <- ressources) ressource.close()
  def apply(key: String): T = ressources(key)

}

/**
  * A ressource mapping that can suport closable and non closable ressources.
  * @param ressources closable or non-closable ressource to manage
  */
class RessourceManager[T](ressources: (String, T)*)
  extends AutoCloseable {

  //we close the closable ressources
  private val closeableTuple = ressources collect { case (s, v: AutoCloseable) => (s, v) }
  val closeableRessources = new CloseableMap(closeableTuple: _*)
  val allRessources: Map[String, T] = Map(ressources: _*)

  //methods
  def apply(key: String): T = allRessources(key)
  override def close(): Unit = closeableRessources.close()
}



