
import java.io.File
import java.io.InputStream

fun main(args : Array<String>) {
  println("Hello, world!")
  val inputStream: InputStream = File("data.txt").inputStream()
  val inputString = inputStream.bufferedReader().use { it.readText() }
  println(inputString)
}
