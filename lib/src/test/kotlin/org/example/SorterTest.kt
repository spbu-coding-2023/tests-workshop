package org.example

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.Duration
import kotlin.random.Random
import kotlin.test.assertFailsWith

class SorterTest {
    private lateinit var sorter : Sorter

    @BeforeEach
    fun setup() {
        sorter = BubbleSorter()
    }

    //Positive scenarios

    //Edge cases
    @Test
    fun emptyArrayShouldBeEmptyAfterSort() {
        sorter = BubbleSorter()

        val testArray = intArrayOf()
        val expectedResult = intArrayOf()
        val actualResult = sorter.sortPositiveNumbers(testArray)

        assert(expectedResult contentEquals actualResult)
        {"Sorting the empty array returned non-empty result"}
    }

    @Test
    fun `sorted array should not change after sort`() {
        val testArray = intArrayOf(1, 2, 3)
        val expectedResult = intArrayOf(1, 2, 3)
        val actualResult = sorter.sortPositiveNumbers(testArray)

        assert(expectedResult contentEquals actualResult)
    }

    @Test
    @DisplayName("anti sorted array should be reversed after sort")
    fun antiSortedArrayShouldBeReversedAfterSort() {
        val testArray = intArrayOf(4, 3, 2, 1)
        val expectedResult = intArrayOf(1, 2, 3, 4)
        val actualResult = sorter.sortPositiveNumbers(testArray)

        assert(expectedResult contentEquals actualResult)
    }

    @Test
    fun arrayWithDuplicatesShouldBeSorted() {
        val testArray = intArrayOf(4, 3, 2, 4)
        val expectedResult = intArrayOf(2, 3, 4, 4)
        val actualResult = sorter.sortPositiveNumbers(testArray)

        assert(expectedResult contentEquals actualResult)
    }

    //Base scenarios

    @Test
    fun oddArrayShouldBeSortedAfterSort() {
        val testArray = intArrayOf(7, 2, 5, 3, 1)
        val expectedResult = intArrayOf(1, 2, 3, 5, 7)
        val actualResult = sorter.sortPositiveNumbers(testArray)

        assert(expectedResult contentEquals actualResult)
    }

    @Test
    fun evenArrayShouldBeSortedAfterSort() {
        val testArray = intArrayOf(7, 2, 5, 3, 1, 8)
        val expectedResult = intArrayOf(1, 2, 3, 5, 7, 8)
        val actualResult = sorter.sortPositiveNumbers(testArray)

        assert(expectedResult contentEquals actualResult)
    }

    @ParameterizedTest
    @MethodSource("times")
    fun sortingShouldNotExceedTimeout(size : Int, seconds : Long) {
   val testArray = IntArray(size) { Random.nextInt(1,100)}
        Assertions.assertTimeout(Duration.ofSeconds(seconds)) { sorter.sortPositiveNumbers(testArray) }
    }

    companion object {
        @JvmStatic
        fun times() = listOf(
            Arguments.of(1000, 1L),
            Arguments.of(40000, 2L)
        )
    }

    //Negative scenarios

    @Test
    fun arrayWithNegativesShouldCauseFailDuringSort() {
        val testArray = intArrayOf(7, 2, -5, 3)
        assertFailsWith<IllegalArgumentException>("Array contains non-positive elements") {
            sorter.sortPositiveNumbers(testArray)
        }
    }

    @RepeatedTest(2)
    fun arrayWithZerosShouldCauseFailDuringSort() {
        val testArray = intArrayOf(7, 0, 4, 3)
        assertFailsWith<IllegalArgumentException>("Array contains non-positive elements") {
            sorter.sortPositiveNumbers(testArray)
        }
    }
}