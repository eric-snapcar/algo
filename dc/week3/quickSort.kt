
import java.io.File
import java.io.InputStream
// kotlinc quickSort.kt -include-runtime -d quickSort.jar
// java -jar quickSort.jar

import java.util.Random

val random = Random()

fun rand(from: Int, to: Int) : Int {
    return random.nextInt(to - from) + from
}

fun main(args : Array<String>) {
  val array = getArray()
  println(quickSort(array))
}
data class QuickSortData(val comparaisons: Int, val sortedArray : List<Int>)
fun quickSort(array:List<Int>) : List<Int>{
  var size = array.size
  if (size > 1) {
    val pivotIndex =  rand(0, size - 1)
    val (pivotValue, leftArray, rightArray) = partition(pivotIndex,array)
    val (comparaisonsLeft, leftArraySorted) = quickSort(leftArray)
    val (comparaisonsRight, rightArraySorted) = quickSort(rightArray)
    return QuickSortData(size - 1 + comparaisonsLeft + comparaisonsRight, leftArraySorted + listOf(pivotValue) + rightArraySorted)
  }else {
    return return QuickSortData(0,array)
  }
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
fun getArray():List<Int>{
  val reader = File("data.txt").inputStream().bufferedReader()
  val iterator = reader.lines().iterator()
  var list = mutableListOf<Int>()
  while(iterator.hasNext()) {
    list.add(iterator.next().toInt())
  }
  reader.close()
  return list
}
