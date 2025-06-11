package coder.greyfei.leetcode

/**
 * Created by GreyFei on 2025/6/9
 */


/**
 * [146. LRU缓存机制](https://leetcode.cn/problems/lru-cache/description/)
 * 实现方式：哈希表 + 双向链表。
 */
private class LRUCache(capacity: Int) {

    private val mCapacity = capacity
    private val mHashMap: HashMap<Int, Node> = hashMapOf()
    private val mLinkedList: CustomLinkedList = CustomLinkedList()

    fun get(key: Int): Int {
        val node = mHashMap.get(key)
        if (node != null) {
            // 把节点提升到最近使用的链表结尾
            mLinkedList.remove(node)
            mLinkedList.addLast(node)
            // 返回值
            return node.value
        }
        return -1
    }

    fun put(key: Int, value: Int) {
        val node = mHashMap.get(key)
        if (node != null) {
            // 已有节点
            // 把节点提升到最近使用的链表结尾
            mLinkedList.remove(node)
            mLinkedList.addLast(node)
            // 更新value
            node.value = value
        } else {
            // 没有节点
            if (mLinkedList.getSize() >= mCapacity) {
                // 容量满了，移除头部节点
                val removedNode = mLinkedList.removeFirst()
                // 从哈希表里移除
                if (removedNode != null) {
                    mHashMap.remove(removedNode.key)
                }
            }

            // 把新节点添加到链表尾部
            val newNode = Node(key, value)
            mLinkedList.addLast(newNode)
            // 添加到哈希表里
            mHashMap.put(key, newNode)
        }
    }

}

private class Node(
    var key: Int,
    var value: Int,
) {

    var prev: Node = EmptyNode
    var next: Node = EmptyNode

    companion object {
        val EmptyNode = Node(0, 0)
    }

}

private class CustomLinkedList {

    private var mSize = 0
    // 伪头部节点
    private var mFirst = Node(0, 0)
    // 伪尾部节点
    private var mLast = Node(0, 0)

    init {
        // 初始化双向链表
        mFirst.next = mLast
        mLast.prev = mFirst
    }

    /**
     * 在双向链表尾部加入新的节点，时间复杂度O(1)
     */
    fun addLast(node: Node) {
        node.prev = mLast.prev
        node.next = mLast

        mLast.prev.next = node
        mLast.prev = node

        mSize++
    }

    /**
     * 在双向链表中移除节点，时间复杂度O(1)
     */
    fun remove(node: Node) {
        node.prev.next = node.next
        node.next.prev = node.prev

        mSize--
    }

    /**
     * 移除双向链表的头部节点，时间复杂度O(1)
     */
    fun removeFirst(): Node? {
        if (mFirst.next === mLast) {
            return null
        }

        val first = mFirst.next
        remove(first)

        return first
    }

    fun getSize() = mSize

}

private fun testCase1() {
    val lruCache = LRUCache(2)
    lruCache.put(1, 1)
    lruCache.put(2, 2)
    assert(lruCache.get(1) == 1)
    lruCache.put(3, 3)
    assert(lruCache.get(2) == -1)
    lruCache.put(4, 4)
    assert(lruCache.get(1) == -1)
    assert(lruCache.get(3) == 3)
    assert(lruCache.get(4) == 4)
    println("testCase1 Pass!")
}

private fun main() {
    testCase1()
}