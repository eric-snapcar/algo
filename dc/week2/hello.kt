// Count the number of inversion of data.txt

import java.io.File
import java.io.InputStream

fun main(args : Array<String>) {
  val array = listOf(1, 2, 3)
  mergeSort(array)
}
fun mergeSort(array:List<Int>){
  println(array)
}
fun test(){
  val inputStream: InputStream = File("data.txt").inputStream()
  val inputString = inputStream.bufferedReader().use { it.readText() }
  println(inputString)
}
