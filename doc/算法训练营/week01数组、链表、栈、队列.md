##### 数组

时间复杂度
```
Lookup O(1)
Insert O(n)
Delete O(n)
Append(push back) O(1)
Prepend(push front) O(n)
```

变长数组：
```
一个简易的方法：
初始化: 空数组,分配常数空间,记录实际长度(size)和容量(capacity)
push back: 若空间不够,重新申请2倍大小的连续空间,拷贝到新空间,释放旧空间
pop back: 若空间利用率(size/capacity) 不到25%,释放一半的空间
```

[去重](https://leetcode.cn/problems/remove-duplicates-from-sorted-array/description/)<br/>
[移动零](https://leetcode.cn/problems/move-zeroes/description/)<br/>
[合并有序数组](https://leetcode.cn/problems/merge-sorted-array/description/)<br/>

##### 链表
时间复杂度
```
Lookup O(n)
Insert O(1)
Delete O(1)
Append(push back) O(1)
Prepend(push front) O(1)
```
[反转链表](https://leetcode.cn/problems/reverse-linked-list/description/)<br/>
[K个一组翻转链表](https://leetcode.cn/problems/reverse-nodes-in-k-group/description/)<br/>
[邻值查找](https://www.acwing.com/problem/content/description/138/)<br/>
[环形链表](https://leetcode.cn/problems/linked-list-cycle/description/)<br/>
[环形链表II](https://leetcode.cn/problems/linked-list-cycle-ii/description)<br/>
##### 栈
栈、队列
```
Push (入栈、入队)  O(1)
Pop (出栈、出队) O(1)
```
双端队列
```
队头、队尾的插入、删除、访问也都是O(1)
```
优先队列
```
访问最值： O(1)
插入:一般是O(logN)
取最值：O(logN)
```
[有效的括号](https://leetcode.cn/problems/valid-parentheses/description/)<br/>
[最小栈](https://leetcode.cn/problems/min-stack/description/)<br/>
[后缀表达式求值](https://leetcode.cn/problems/evaluate-reverse-polish-notation/description/)<br/>
[中缀表达式求值-基本计算器 II](https://leetcode.cn/problems/basic-calculator-ii/description/)<br/>
[中缀表达式求值-基本计算器](https://leetcode.cn/problems/basic-calculator/description/)<br/>

##### 队列
##### 单调栈
[柱状图中最大矩阵](https://leetcode.cn/problems/largest-rectangle-in-histogram/description/)<br/>
```
朴素算法(时间复杂度O(N*N)):
以每个柱子的高度为限制,固定高度,向左右两侧扩展,扩展到左右第一个比它矮的矩形

思考方向:
1.以一个矩形为确定的高度,向两侧扩展的范围:左右第一个比它矮的矩形
2.若矩形高度单调递增,答案是什么? 当前的height * 累加到最后一个矩形的宽度
3.来了一个矩形破坏了单调性,会发生什么? 无法继续扩展,只能贡献宽度

单调栈:尾部插入删除 + 维护单调性(时间复杂度O(N))
1.满足单调性,直接入栈即可
2.单调性破坏,栈顶元素删除,栈顶元素左右扩展的范围已经确定,用高度 * 扩展范围更新答案,同时累加宽度
```
[接雨水](https://leetcode.cn/problems/trapping-rain-water/description/)<br/>
```
柱状图中最大矩阵:单调递增栈,到了一个矮的地方,跨不过去了,就会超过矩形的范围,所以需要维护一个单调递增的栈。   
接雨水: a.把水看成横条,向两测扩展，到了一个更高的地方就扩展不了，所以维护一个递减的单调栈。(水往低处流)
       b.把水看成竖条,向两侧扩展,前后缀最大值的较小者(木桶原理)
```
##### 单调队列
[滑动窗口最大值](https://leetcode.cn/problems/sliding-window-maximum/description/)<br/>
```
递减的数据结构（不递减，小的就冗余），从左边删除 右边插入、删除的数据结构，所以考虑是双端队列
所以本题维护的是单调递减的双端队列
```





