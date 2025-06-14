package coder.greyfei.leetcode._912

import kotlin.random.Random

/**
 * [912. 排序数组](https://leetcode.cn/problems/sort-an-array/description/)
 *
 * Created by GreyFei on 2025/6/14
 */


/**
 * 解法：快排。
 * 取中枢值、划分数组、快慢指针、递归
 */
private class Solution {
    fun sortArray(nums: IntArray): IntArray {
        quickSort(nums, 0, nums.size - 1)
        return nums
    }

    private fun quickSort(nums: IntArray, start: Int, end: Int) {
        // 递归终点
        if (start >= end) return

        // 选择中枢值
        val randomPivotPos = Random.nextInt(start, end + 1)
        val pivot = nums[randomPivotPos]
        // 将中枢值放到数组尾部
        nums.swap(randomPivotPos, end)

        // 快慢指针，根据中枢值划分数组
        var slow = start - 1
        var quick = start
        while (quick <= end - 1) {
            if (nums[quick] <= pivot) {
                // 小于中枢值，交换slow + 1, quick位置的值
                slow++
                nums.swap(slow, quick)

                // 保证 [start, slow] 位置都 <= pivot
                // [slow + 1, quick] 位置都 > pivot
            }
            // 快指针前进
            quick++
        }

        // 中枢值所在的位置，即为最终所在的有序数组的位置
        val realPivotPos = slow + 1
        // 将中枢值交换到 slow + 1 的位置
        nums.swap(realPivotPos, end)

        // 递归
        quickSort(nums, start, realPivotPos - 1)
        quickSort(nums, realPivotPos + 1, end)
    }

    private fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

}

/**
 * 解法：堆排序。
 */
private class Solution2 {

    fun sortArray(nums: IntArray): IntArray {
        heapSort(nums)
        return nums
    }

    private fun heapSort(nums: IntArray) {
        // 1、构造初始堆，即初始数组 nums

        // 2、调整成大顶堆
        // 从第一个非叶节点开始调整，自底向上，自右到左
        val firstNonLeafNode = nums.size / 2 - 1
        for (i in firstNonLeafNode downTo 0) {
            maxHeapify(nums, i, nums.size - 1)
        }

        // 3、排序
        for (endIdx in (nums.size - 1) downTo 1) {
            // 把堆顶元素（当前最大）交换到需要排序的数组末尾
            nums.swap(0, endIdx)
            // 对剩余需要排序的数组调整成大顶堆
            maxHeapify(nums, 0, endIdx - 1)
        }
    }

    private fun maxHeapify(nums: IntArray, k: Int, endIdx: Int) {
        var curIdx = k
        var childIdx = curIdx * 2 + 1

        while (childIdx <= endIdx) {
            if (childIdx + 1 <= endIdx && nums[childIdx + 1] > nums[childIdx]) {
                // 找出左右子节点中的较大值
                childIdx++
            }
            if (nums[childIdx] > nums[curIdx]) {
                // 子节点的值大于父节点，交换父子节点
                nums.swap(childIdx, curIdx)
                // 从下一个子节点继续循环调整
                curIdx = childIdx
                childIdx = curIdx * 2 + 1
            } else {
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

/**
 * 解法：归并排序
 */
private class Solution3 {
    fun sortArray(nums: IntArray): IntArray {
        mergeSort(nums, 0, nums.size - 1)
        return nums
    }

    private fun mergeSort(nums: IntArray, start: Int, end: Int) {
        if (start >= end) {
            return
        }

        val middle = (end + start) / 2
        mergeSort(nums, start, middle)
        mergeSort(nums, middle + 1, end)

        // 合并左右两个已排序的子数组
        var i = start
        var j = middle + 1
        val sortedArr = IntArray(nums.size)
        var idx = 0
        while (i <= middle && j <= end) {
           if (nums[i] <= nums[j]) {
               sortedArr[idx++] = nums[i]
               i++
           } else {
               sortedArr[idx++] = nums[j]
               j++
           }
        }
        while (i <= middle) {
            sortedArr[idx++] = nums[i++]
        }
        while (j <= end) {
            sortedArr[idx++] = nums[j++]
        }

        for (k in 0..(end - start)) {
            nums[start + k] = sortedArr[k]
        }
    }

}