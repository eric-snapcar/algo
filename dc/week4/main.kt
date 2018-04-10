
import java.io.File
import java.io.InputStream
// kotlinc main.kt -include-runtime -d main.jar
// java -jar main.jar
import java.util.Random
val random = Random()
fun main(args : Array<String>) {
  val vertices = getVertices()
  println(getRandomElements(vertices,2));
  println(getRandomElements(vertices,2));
  println(getRandomElements(vertices,2));
}
fun getRandomElements(list:  List<Any>,numberOfElements: Int): List<Any>? {
    if (numberOfElements > list.size) {
        return null
    }
    return list.shuffled().take(numberOfElements)
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



/*
fun main(args: Array<String>) {
    val list = listOf(1, 16, 3, 7, 17, 24, 34, 23, 11, 2)
    println("The list consists of the following numbers:\n${list}")

    // notice we can call our extension functions as if they were regular member functions of List
    println("\nA randomly selected element from the list is ${list.getRandomElement()}")
    println("\nA random sequence of 5 elements from the list is ${list.getRandomElements(5)}")
}*/
