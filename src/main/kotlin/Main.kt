import java.io.File

fun main() {
//	val input = File("input.txt").readLines()
	val input = File("input.txt").readText()

	println(Day1.puzzle1(input))
	println(Day1.puzzle2(input))
}
