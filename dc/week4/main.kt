
import java.io.File
import java.io.InputStream
// kotlinc main.kt -include-runtime -d main.jar
// java -jar main.jar
import java.util.Random
val random = Random()
fun main(args : Array<String>) {
  val vertices = getVertices()
  println(vertices[0]);
  println(vertices[1]);
  println(Vertex.merge(vertices[0],vertices[1]))
  val list = listOf(1, 16, 3, 7, 17, 24, 34, 23, 11, 2)
  println(getRandomElements(list,2));
  println(getRandomElements(list,2));
  println(getRandomElements(list,2));
/*
  println("The list consists of the following numbers:\n${list}")

  // notice we can call our extension functions as if they were regular member functions of List
  println("\nA randomly selected element from the list is ${list.getRandomElement()}")
  println("\nA random sequence of 5 elements from the list is ${list.getRandomElements(5)}")
  */
}

fun getRandomElements(list:  List<Any>,numberOfElements: Int): List<Any>? {
    if (numberOfElements > list.size) {
        return null
    }
    return list.shuffled().take(numberOfElements)
}
data class Vertex( val origin : String , val edges :  List<String>){
  /*
    init {
      println("Vertex init")
      println(edges)
      println(origin)
    }
    */
    constructor(list : List<String> ) : this(list[0],list.subList(1,list.size)) {
    }
    companion object {
      fun merge(vertex1 : Vertex, vertex2 : Vertex): Vertex {
        val origin1 = vertex1.origin
        val origin2 = vertex2.origin
        val edges1 = vertex1.edges
        val edges2 = vertex2.edges



        val edges = (edges2 + edges1).filter { s -> s != origin1 || s != origin2 }
        // Enlever les boucles internes en filtrant origin1 et origin2 de edges1 et edges2
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
fun quickSort(array:List<Int>) : QuickSortData {
  var size = array.size
  if (size > 1) {
    val pivotIndex =  rand(0, size - 1)
    val (pivotValue, leftArray, rightArray) = partition(pivotIndex,array)
    val (comparaisonsLeft, leftArraySorted) = quickSort(leftArray)
    val (comparaisonsRight, rightArraySorted) = quickSort(rightArray)
    return QuickSortData(size - 1 + comparaisonsLeft + comparaisonsRight, leftArraySorted + listOf(pivotValue) + rightArraySorted)
  }else {
    return QuickSortData(0,array)
  }
}
*/
fun mergeVertex(vertex1 : List<String>,vertex2 : List<String>): List<String> {
  return vertex1
}
data class PartitionData(val pivotValue: Int, val leftArray: List<Int>, val rightArray: List<Int> )
fun partition(pivotIndex : Int,array : List<Int>): PartitionData {
  val pivotValue = array[pivotIndex]
  var leftArray = mutableListOf<Int>()
  var rightArray = mutableListOf<Int>()
  for (value: Int in array) {
    if(value < pivotValue ){
      leftArray.add(value)
    }
    else if(value > pivotValue){
      rightArray.add(value)
    }
  }
  return PartitionData(pivotValue,leftArray,rightArray)
}
fun rand(from: Int, to: Int) : Int {
    return random.nextInt(to - from) + from
}


/*
fun main(args: Array<String>) {
    val list = listOf(1, 16, 3, 7, 17, 24, 34, 23, 11, 2)
    println("The list consists of the following numbers:\n${list}")

    // notice we can call our extension functions as if they were regular member functions of List
    println("\nA randomly selected element from the list is ${list.getRandomElement()}")
    println("\nA random sequence of 5 elements from the list is ${list.getRandomElements(5)}")
}*/
