import java.io.File

fun main() {
//	val input = File("input.txt").readLines()
	val input = File("input.txt").readText()

	println(Day13.puzzle1(input))
	println(Day13.puzzle2(input))
}
