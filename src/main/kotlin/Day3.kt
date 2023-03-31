object Day3 {
	private fun priorityOf(item: Char?): Int =
		when (item) {
			null -> 0
			in 'a'..'z' -> item.code - 96
			in 'A'..'Z' -> item.code - 38
			else -> 0
		}

	fun puzzle1(input: List<String>): Int {
		return input.sumOf { sack ->
			val first = sack.substring(0 until sack.length / 2)
			val second = sack.substring(sack.length / 2)
			priorityOf(first.find { it in second })
		}
	}

	fun puzzle2(input: List<String>): Int {
		return input.chunked(3).sumOf { group ->
			priorityOf(
				group[0].find {
					(it in group[1]) and (it in group[2])
				},
			)
		}
	}
}