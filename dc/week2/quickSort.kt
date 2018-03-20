
import java.io.File
import java.io.InputStream
// kotlinc quickSort.kt -include-runtime -d quickSort.jar
// java -jar quickSort.jar
fun main(args : Array<String>) {
  val array = getArray()
  // println(quickSort(array))
}
fun quickSort(array:List<Int>) : List<Int>{
  var size = array.size
  if (size > 1) {
    val pivotIndex =  size/2
    val (pivotValue, leftArray, rightArray) = partition(pivotIndex,array)
    return quickSort(leftArray) + listOf(pivotValue) + quickSort(rightArray)
  }else {
    return array
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
fun merge(leftArraySorted:List<Int>,rightArraySorted:List<Int>): List<Int>{
  var mergeSorted = mutableListOf<Int>()
  var leftArraySorted_ = leftArraySorted.toMutableList()
  var rightArraySorted_ = rightArraySorted.toMutableList()
  while(leftArraySorted_.size > 0 || rightArraySorted_.size > 0){
    if(leftArraySorted_.size == 0 ){
      mergeSorted.add(rightArraySorted_.first())
      rightArraySorted_.removeAt(0)
    }else if(rightArraySorted_.size == 0){
      mergeSorted.add(leftArraySorted_.first())
      leftArraySorted_.removeAt(0)
    }
    else {
      if(leftArraySorted_.first() < rightArraySorted_.first()){
        mergeSorted.add(leftArraySorted_.first())
        leftArraySorted_.removeAt(0)
      }else {
        mergeSorted.add(rightArraySorted_.first())
        rightArraySorted_.removeAt(0)
      }
    }
  }
  return mergeSorted
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
