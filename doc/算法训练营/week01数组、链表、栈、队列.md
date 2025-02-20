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
##### 单调队列
[滑动窗口最大值](https://leetcode.cn/problems/sliding-window-maximum/description/)<br/>
[接雨水](https://leetcode.cn/problems/trapping-rain-water/description/)<br/>





