private typealias PacketPair = Pair<Day13.PacketData, Day13.PacketData>

object Day13 {
	fun puzzle1(input: String): Int {
		parsePacketPairs(input).forEach(::println)
		return 0
	}

	fun puzzle2(input: String): Int {
		return 0
	}

	private fun splitPacketDataList(input: String): List<String> {
		var bracketsCount = 0
		var currentPacketData = ""
		val packetDataList = mutableListOf<String>()

		for (c in input) {
			currentPacketData += c
			when (c) {
				'[' -> bracketsCount++
				']' -> bracketsCount--
				',' -> if (bracketsCount == 0) {
					packetDataList.add(currentPacketData.removeSuffix(","))
					currentPacketData = ""
				}
			}
		}
		packetDataList.add(currentPacketData)

		return packetDataList
	}

	private fun parsePacketData(input: String): PacketData {
		return if (input.isBlank()) {
			PacketData.List(emptyList())
		} else if (input.startsWith("[")) {
			input.substring(1 until input.length - 1).let {
				PacketData.List(
					it.run(this::splitPacketDataList).map(this::parsePacketData)
				)
			}
		} else {
			PacketData.Int(input.toInt())
		}
	}

	private fun parsePacketPairs(input: String): List<PacketPair> {
		return input.split("\n\n").map { pair ->
			pair.split("\n").let {
				this.parsePacketData(it[0]) to this.parsePacketData(it[1])
			}
		}
	}

	private fun comparePacketPair(packets: PacketPair): Boolean {
		val (left, right) = packets
		when {
			left is PacketData.Int && right is PacketData.Int -> {
				return left.value <= right.value
			}

			left is PacketData.List && right is PacketData.List -> {
			}
		}
		
		return false
	}

	private fun comparePacketPairs(packets: List<PacketPair>): Int {
		return 0
	}

	sealed class PacketData {
		data class List(val value: kotlin.collections.List<PacketData>) : PacketData()
		data class Int(val value: kotlin.Int) : PacketData()
	}
}