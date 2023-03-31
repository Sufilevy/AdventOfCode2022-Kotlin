object Day1 {
	fun puzzle1(input: String): Int {
		return input
			.split("\r\n\r\n")
			.maxOf { elf ->
				elf.lines()
					.sumOf {
						it.toInt()
					}
			}
	}

	fun puzzle2(input: String): Int {
		return input
			.split("\r\n\r\n")
			.map { elf ->
				elf.lines()
					.sumOf {
						it.toInt()
					}
			}.sortedDescending().take(3).sum()
	}
}