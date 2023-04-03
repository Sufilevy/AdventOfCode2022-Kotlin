import java.util.PriorityQueue

object Day12 {
	private var hillClimbing: HillClimbing? = null

	fun puzzle1(input: List<String>): Int {
		this.hillClimbing = HillClimbing(input)
		return hillClimbing!!.findShortestPath()
	}

	fun puzzle2(input: List<String>): Int {
		return hillClimbing!!
			.getStartPoints()
			.minOf {
				hillClimbing!!.findShortestPath(it)
			}
	}
}

class HillClimbing(input: List<String>) {
	private val rocks: Array<IntArray>
	private val startPoint: Point
	private val endPoint: Point

	init {
		var (startPoint, endPoint) = Point() to Point()
		this.rocks = Array(input.size) { y ->
			input[y].mapIndexed { x, char ->
				when (char) {
					'S' -> {
						startPoint = Point(x, y)
						0
					}

					'E' -> {
						endPoint = Point(x, y)
						27
					}

					else -> char.code - 96
				}
			}.toIntArray()
		}

		this.startPoint = startPoint
		this.endPoint = endPoint
	}

	private val height = this.rocks.size
	private val width = this.rocks.first().size

	private fun getHeightOf(point: Point): Int = this.rocks[point.y][point.x]
	private fun getHeightOf(x: Int, y: Int): Int = this.rocks[y][x]

	fun getStartPoints(): List<Point> {
		val startPoints = mutableListOf(this.startPoint)

		this.rocks.forEachIndexed { y, row ->
			row.forEachIndexed { x, height ->
				if (height == 1) startPoints.add(Point(x, y))
			}
		}

		return startPoints
	}


	private fun getNeighboursOf(point: Point): List<Point> {
		val neighbours = mutableListOf<Point>()
		val maxHeight = this.getHeightOf(point) + 1

		val (left, right, up, down) = arrayOf(point.x - 1, point.x + 1, point.y - 1, point.y + 1)

		if (left >= 0 && this.getHeightOf(left, point.y) <= maxHeight)
			neighbours.add(Point(left, point.y))

		if (right < this.width && this.getHeightOf(right, point.y) <= maxHeight)
			neighbours.add(Point(right, point.y))

		if (up >= 0 && this.getHeightOf(point.x, up) <= maxHeight)
			neighbours.add(Point(point.x, up))

		if (down < this.height && this.getHeightOf(point.x, down) <= maxHeight)
			neighbours.add(Point(point.x, down))

		return neighbours
	}

	fun findShortestPath(startPoint: Point): Int {
		val visitedPoints = mutableSetOf<Point>()
		val pushedPoints = mutableSetOf<Point>()

		val toVisit = PriorityQueue<Point> { p1, p2 -> p1.cost - p2.cost }
		toVisit.add(startPoint)

		while (toVisit.isNotEmpty()) {
			val currentPoint = toVisit.poll()
			visitedPoints.add(currentPoint)

			val neighbours = this.getNeighboursOf(currentPoint)

			for (it in neighbours) {
				if (it in visitedPoints || it in pushedPoints) continue

				it.cost = currentPoint.cost + 1
				toVisit.add(it)
				pushedPoints.add(it)
			}
		}

		return visitedPoints.find { it == endPoint }?.cost ?: Int.MAX_VALUE
	}

	fun findShortestPath(): Int {
		return this.findShortestPath(this.startPoint)
	}

	data class Point(
		val x: Int = -1,
		val y: Int = -1,
		var cost: Int = 0
	) {
		override fun equals(other: Any?): Boolean =
			other is Point && this.x == other.x && this.y == other.y

		override fun hashCode(): Int {
			var result = x
			result = 31 * result + y
			return result
		}
	}
}