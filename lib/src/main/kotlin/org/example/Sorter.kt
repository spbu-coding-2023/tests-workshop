package org.example

interface Sorter {
    fun sortPositiveNumbers(keys: IntArray): IntArray
}

class BubbleSorter : Sorter {

    override fun sortPositiveNumbers(keys: IntArray): IntArray {

        if (keys.any { it <= 0 }) {
            throw IllegalArgumentException("Array contains non-positive elements")
        }

        for (i in keys.indices) {
            for (j in i + 1 until keys.size) {
                if (keys[i] > keys[j]) {

                    val tmp = keys[i]
                    keys[i] = keys[j]
                    keys[j] = tmp
                }
            }
        }

        return keys
    }
}
