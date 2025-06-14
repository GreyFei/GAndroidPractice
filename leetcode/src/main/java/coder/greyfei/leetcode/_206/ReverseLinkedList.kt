package coder.greyfei.leetcode._206

/**
 * [206. 反转链表](https://leetcode.cn/problems/reverse-linked-list/description/)
 *
 * Created by GreyFei on 2025/6/10
 */

private interface ISolution {
    fun reverseList(head: ListNode?): ListNode?
}

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
private class Solution : ISolution {
    /**
     * 实现方式：迭代。
     */
    override fun reverseList(head: ListNode?): ListNode? {
        head?.next ?: return head

        var prev: ListNode? = null
        var curr: ListNode? = head
        while (curr != null) {
            // 存储下一个节点
            val next = curr.next

            // 反转链表方向
            curr.next = prev

            // 移动双指针
            prev = curr
            curr = next
        }

        return prev
    }
}

private class Solution2 : ISolution {
    /**
     * 实现方式：递归。
     */
    override fun reverseList(head: ListNode?): ListNode? {
        head?.next ?: return head

        val newHead = reverseList(head.next)
        head.next?.next = head
        head.next = null
        return newHead
    }
}

private class ListNode(var value: Int) {
    var next: ListNode? = null
}

private fun ListNode.link(value: Int): ListNode {
    val newNode = ListNode(value)
    this.next = newNode
    return newNode
}

private fun ListNode?.toListString(): String {
    val sb = StringBuilder("[")
    var cur: ListNode? = this
    while (cur != null) {
        sb.append("${cur.value}")
        if (cur.next != null) {
            sb.append(", ")
        }
        cur = cur.next
    }
    sb.append("]")
    return sb.toString()
}

private fun testCase1(solution: ISolution) {
    val head = ListNode(1)
    head.link(2).link(3).link(4).link(5)
    println("Original List=${head.toListString()}")

    val reversedHead = solution.reverseList(head)
    println("Reversed List=${reversedHead.toListString()}")

    assert(reversedHead.toListString() == "[5, 4, 3, 2, 1]")
    println("testCase1 Pass!")
}

private fun testCase2(solution: ISolution) {
    val head = ListNode(1)
    head.link(2)
    println("Original List=${head.toListString()}")

    val reversedHead = solution.reverseList(head)
    println("Reversed List=${reversedHead.toListString()}")

    assert(reversedHead.toListString() == "[2, 1]")
    println("testCase2 Pass!")
}

private fun testCase3(solution: ISolution) {
    val head: ListNode? = null

    println("Original List=${head.toListString()}")

    val reversedHead = solution.reverseList(head)
    println("Reversed List=${reversedHead.toListString()}")

    assert(reversedHead.toListString() == "[]")
    println("testCase3 Pass!")
}

private fun main() {
    println("Solution1: 迭代法")
    val solution = Solution()
    testCase1(solution); println()
    testCase2(solution); println()
    testCase3(solution); println()

    println("Solution2: 递归法")
    val solution2 = Solution2()
    testCase1(solution2); println()
    testCase2(solution2); println()
    testCase3(solution2); println()
}