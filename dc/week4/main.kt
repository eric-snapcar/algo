
import java.io.File
import java.io.InputStream
// kotlinc main.kt -include-runtime -d main.jar
// java -jar main.jar
import java.util.Random
val random = Random()
fun main(args : Array<String>) {
  val array = getArray()
  println(array.size)

  println(array[0])
  println(array[1])
  println(array[1].edges.size)
  println(array[2])
  println(array[2].edges.size)

  // println(mergeVertex(array[0],array[1]))
}
data class QuickSortData(val comparaisons: Int, val sortedArray : List<Int>)

// data class Vertex(val origin: String, val edges : List<String>)

class Vertex( list : List<String> ){
    val edges = list.subList(1,list.size);
    val origin = list[0]
    init {
      //
    }
}
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
fun getArray():List<Vertex>{
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
fun rand(from: Int, to: Int) : Int {
    return random.nextInt(to - from) + from
}
