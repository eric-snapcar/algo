// Count the number of inversion of data.txt

import java.io.File
import java.io.InputStream

fun main(args : Array<String>) {
  val array = getArray()
  println(mergeSort(array))
}
data class InversionData(val count: Int, val sortedList: List<Int> )
fun countInversion(array:List<Int>) : List<Int>{
  var size = array.size
  if (size > 1) {
    val halfSize = array.size/2
    val leftArray = array.subList(0,halfSize)
    val rightArray = array.subList(halfSize,size)
    val (leftArrayCount,leftArraySorted) = countInversion(leftArray)
    val (rightArrayCount,rightArraySorted) = countInversion(rightArray)
    val (splitCount,arraySorted) = countInversionMerge(leftArraySorted,rightArraySorted)
    return InversionData(leftArrayCount+rightArrayCount+splitCount,arraySorted)
  }else {
    return InversionData(array,1)
  }
}
fun countInversionMerge(leftArraySorted:List<Int>,rightArraySorted:List<Int>): List<Int>{
  var mergeSorted = mutableListOf<Int>()
  var splitCount = 0
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
  return InversionData(mergeSorted,splitCount)
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
