object Day2 {

	fun puzzle1(input: List<String>): Int {
		fun scoreRound(round: List<String>) =
			when (round[0] to round[1]) {
				"A" to "X" -> 4
				"A" to "Y" -> 8
				"A" to "Z" -> 3
				"B" to "X" -> 1
				"B" to "Y" -> 5
				"B" to "Z" -> 9
				"C" to "X" -> 7
				"C" to "Y" -> 2
				"C" to "Z" -> 6
				else -> 0
			}

		return input.sumOf {
			scoreRound(it.split(" "))
		}
	}

	fun puzzle2(input: List<String>): Int {
		fun scoreRound(round: List<String>) =
			when (round[0] to round[1]) {
				"A" to "X" -> 3
				"A" to "Y" -> 4
				"A" to "Z" -> 8
				"B" to "X" -> 1
				"B" to "Y" -> 5
				"B" to "Z" -> 9
				"C" to "X" -> 2
				"C" to "Y" -> 6
				"C" to "Z" -> 7
				else -> 0
			}

		return input.sumOf {
			scoreRound(it.split(" "))
		}
	}
}