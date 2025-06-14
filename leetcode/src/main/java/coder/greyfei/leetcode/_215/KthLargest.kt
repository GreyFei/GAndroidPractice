package coder.greyfei.leetcode._215

import kotlin.random.Random

/**
 * [215. 数组中的第K个最大元素](https://leetcode.cn/problems/kth-largest-element-in-an-array/description/)
 * Created by GreyFei on 2025/6/13
 */


/**
 * 解法一：暴力解法，先排序，再索引下标
 * 如果系统用的是快排，那么时间复杂度：O(nlogn)
 */
private class SolutionJoke {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        val sortedNums = nums.sorted()
        return sortedNums[nums.size - k]
    }
}

/**
 * 解法二：基于快排改进的快速选择
 */
private class Solution {

    fun findKthLargest(nums: IntArray, k: Int): Int {
        return quickSelect(nums, 0, nums.size - 1, nums.size - k)
    }

    private fun quickSelect(nums: IntArray, start: Int, end: Int, k: Int): Int {
        if (start >= end) {
            return nums[k]
        }

        val randomPivotPos = Random.nextInt(start, end + 1)
        val pivot = nums[randomPivotPos]
        nums.swap(randomPivotPos, end)

        var slow = start - 1
        var quick = start

        while (quick <= end - 1) {
            if (nums[quick] <= pivot) {
                slow++
                nums.swap(slow, quick)
            }
            quick++
        }

        val realPivotPos = slow + 1
        nums.swap(realPivotPos, end)

        if (realPivotPos == k) {
            return nums[realPivotPos]
        } else if (k < realPivotPos) {
            return quickSelect(nums, start, realPivotPos - 1, k)
        } else {
            return quickSelect(nums, realPivotPos + 1, end, k)
        }
    }

    private fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

}

/**
 * 解法二：快排改进的快速选择
 * 官方的解法之一，虽然执行时间很快，但是有点难懂，后续有时间再理解。
 */
private class Solution2 {

    fun findKthLargest(nums: IntArray, k: Int): Int {
        return quickSelect(nums, 0, nums.size - 1, nums.size - k)
    }

    private fun quickSelect(nums: IntArray, l: Int, r: Int, k: Int): Int {
        if (l == r) return nums[k]

        val x = nums[l]
        var i = l - 1
        var j = r + 1

        while (i < j) {
            do i++ while (nums[i] < x)
            do j-- while (nums[j] > x)
            if (i < j) {
                // 交换
                val temp = nums[i]
                nums[i] = nums[j]
                nums[j] = temp
            }
        }

        return if (k <= j) {
            quickSelect(nums, l, j, k)
        } else {
            quickSelect(nums, j + 1, r, k)
        }
    }

}

/**
 * 解法三：基于堆排序的选择
 */
private class Solution3 {
    fun findKthLargest(nums: IntArray, k: Int): Int {
        return maxHeapSelect(nums, nums.size - k)
    }

    private fun maxHeapSelect(nums: IntArray, kMaxIndex: Int): Int {
        // 1、构造初始堆，即数组 nums

        // 2、构造大顶堆
        val firstNonLeafNodeIdx = nums.size / 2 - 1
        for (i in firstNonLeafNodeIdx downTo 0) {
            maxHeapify(nums, i, nums.size - 1)
        }

        // 3、查找第K大元素
        for (endIdx in (nums.size - 1) downTo 0) {
            // 把堆顶元素，交换到需要排序的数组末尾
            nums.swap(0, endIdx)

            if (endIdx == kMaxIndex) {
                // 末尾元素是K大元素，结束循环
                break
            }

            // 对 [0, endIdx - 1] 的数组调整成大顶堆
            maxHeapify(nums, 0, endIdx - 1)
        }

        return nums[kMaxIndex]
    }

    private fun maxHeapify(nums: IntArray, startIdx: Int, endIdx: Int) {
        var curIdx = startIdx
        var childIdx = curIdx * 2 + 1

        while (childIdx <= endIdx) {
            val siblingIdx = childIdx + 1
            if (siblingIdx <= endIdx && nums[siblingIdx] > nums[childIdx]) {
                childIdx = siblingIdx
            }
            if (nums[childIdx] > nums[curIdx]) {
                nums.swap(childIdx, curIdx)
                curIdx = childIdx
                childIdx = curIdx * 2 + 1
            } else {
                // 已经是大顶堆
                break
            }
        }
    }

    private fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }
}


private fun main() {
//    println(Solution().findKthLargest(intArrayOf(3, 2, 1, 5, 6, 4), 2))
    println(Solution3().findKthLargest(intArrayOf(3, 2, 1, 5, 6, 4), 2))
}