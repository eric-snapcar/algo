
import java.io.File
import java.io.InputStream
// kotlinc main.kt -include-runtime -d main.jar
// java -jar main.jar
import java.util.Random
val random = Random()
fun main(args : Array<String>) {
  var vertices = getVertices()
  var temp = getRandom(vertices,2)
  var chosenVertices = temp[0]
  var remainingVertices = temp[1]
  println(chosenVertices.size)
  println(remainingVertices.size)
}


fun getRandom(list:  List<Any>,number: Int): List<List<Any>> {
    if (number > list.size) {
        return listOf(listOf(), list)
    }
    val listShuffled = list.shuffled()
    return listOf(listShuffled.take(number), listShuffled.drop(number))
}
data class Vertex( val origin : String , val edges :  List<String>){
    constructor(list : List<String> ) : this(list[0],list.subList(1,list.size)) {
    }
    companion object {
      fun merge(vertex1 : Vertex, vertex2 : Vertex): Vertex {
        val origin1 = vertex1.origin
        val origin2 = vertex2.origin
        val edges1 = vertex1.edges
        val edges2 = vertex2.edges
        val edges = (edges2 + edges1).filter { s -> s != origin1 || s != origin2 }
        val vertex = Vertex(origin1,edges)
        return vertex
      }
    }
}
fun getVertices():List<Vertex>{
  val reader = File("data.txt").inputStream().bufferedReader()
  val iterator = reader.lines().iterator()
  var list = mutableListOf<Vertex>()
  while(iterator.hasNext()) {
    val list_ = iterator.next().split("	").dropLast(1)
    val vertex = Vertex(list_)
    list.add(vertex)
  }
  reader.close()
  return list
}
