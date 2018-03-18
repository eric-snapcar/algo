
import java.io.File
import java.io.InputStream

fun main(args : Array<String>) {
  test()
}
fun test(){
  val inputStream: InputStream = File("data.txt").inputStream()
  val inputString = inputStream.bufferedReader().use { it.readText() }
println(inputString)
}
