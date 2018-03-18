// Count the number of inversion of data.txt

import java.io.File
import java.io.InputStream

fun main(args : Array<String>) {
  val array = listOf(1, 2, 3)
  println(mergeSort(array))
}
fun mergeSort(array:List<Int>) : List<Int>{
  var size = array.size
  if (size > 1) {
    val halfSize = array.size/2
    val leftArray = array.subList(0,halfSize)
    val rightArray = array.subList(halfSize,size)
    return merge(leftArray,rightArray)
  }else {
    println("size 1")
    return array
  }
}
fun merge(leftArraySorted:List<Int>,rightArraySorted:List<Int>): List<Int>{
  return leftArraySorted
}
fun test(){
  val inputStream: InputStream = File("data.txt").inputStream()
  val inputString = inputStream.bufferedReader().use { it.readText() }
  println(inputString)
}
