package com.whatiamdoing.core_ui

import com.archoid.core_ui.feature.StateDiffer
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StateDifferTest {

	@Test
	fun `check StateDiffer working WHEN initialState is 1 and newState is 2 EXPECTED wasIfChangedBlockInvoked = true`() {
		val initialState = 1
		val newState = 2
		val differ = StateDiffer(
			getCurrentViewState = { initialState }
		)
		var wasIfChangedBlockInvoked = false

		differ.with(state = newState) {
			ifChanged(
				accessor = { it }
			) {
				wasIfChangedBlockInvoked = true
			}
		}

		assertEquals(wasIfChangedBlockInvoked, true)
	}

	@Test
	fun `check StateDiffer working WHEN initialState and newState is same EXPECTED wasIfChangedBlockInvoked = false`() {
		val initialState = 1
		val newState = 1
		val differ = StateDiffer(
			getCurrentViewState = { initialState }
		)
		var wasIfChangedBlockInvoked = false

		differ.with(state = newState) {
			ifChanged(
				accessor = { it }
			) {
				wasIfChangedBlockInvoked = true
			}
		}

		assertEquals(wasIfChangedBlockInvoked, false)
	}

}