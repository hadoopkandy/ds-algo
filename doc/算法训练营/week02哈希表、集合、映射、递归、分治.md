##### 哈希表

时间复杂度
```
期望:插入、查询、删除 O(1)
     数据分布比较均衡时
最坏:插入、查询、删除 O(n)
    数据全部被映射为相同的Hash值时
```

##### 集合与映射
```
集合(set) 存储不重复的元素
  有序集合,遍历时按元素大小排列，一般用平衡二叉搜索树实现,O(logN)
  无序集合,一般hash实现,O(1)
  
映射(map) 存储关键码(key) 不重复的键值对（key-value pair）
  有序映射,遍历时按照key大小排列,一般用平衡二叉搜索树实现,O(logN)
  无序映射,一般用哈希表实现,O(1)
对于语言内置的类型(int,string),已经有默认优秀的hash函数,可以直接放进set/map里使用
```
[两数之和](https://leetcode.cn/problems/two-sum/description/)<br/>
[模拟行走机器人](https://leetcode.cn/problems/walking-robot-simulation/description/)<br/>
[字母异位词分组](https://leetcode.cn/problems/group-anagrams/description/)<br/>
[串联所有单词的子串](https://leetcode.cn/problems/substring-with-concatenation-of-all-words/description/)<br/>
[LRU缓存](https://leetcode.cn/problems/lru-cache/description/)<br/>

##### 递归
```
递归的三个关键：
定义需要递归的问题（重叠子问题）--数学归纳法思维
确定递归边界
保护与还原现场
```
[子集](https://leetcode.cn/problems/subsets/description/)<br/>
[组合](https://leetcode.cn/problems/combinations/description/)<br/>
[全排列](https://leetcode.cn/problems/permutations/description/)<br/>
```
子集: 考虑每个数，选还是不选。例如，大体积背包问题，考虑每个物品放还是不放。
组合: 在集合里选一个东西
排列: 考虑每个位置选什么样的数，在没有选择的数里面选一个，所以维护选没选过的共享信息。
```
[翻转二叉树](https://leetcode.cn/problems/invert-binary-tree/description/)<br/>
[验证二叉搜索树](https://leetcode.cn/problems/validate-binary-search-tree/description/)<br/>
```
重叠子问题：翻转or验证左、右子树
当前层逻辑:翻转or验证大小关系
递归边界:叶子结点(无子树)
```
[二叉树的最大深度](https://leetcode.cn/problems/maximum-depth-of-binary-tree/description/)<br/>
[二叉树的最小深度](https://leetcode.cn/problems/minimum-depth-of-binary-tree/description/)<br/>
```
思路一：自底向上统计信息,分治思想
最大深度=max(左子树最大深度,右子树最大深度) + 1

思路二：自顶向下维护信息
把"深度" 作为一个全局变量（一个跟随结点移动而动态变化的信息）
递归一层,变量+1,在叶子处更新答案
这种写法需要注意保护和还原现场
```
##### 分治
```
分治,即"分而治之"
就是把原问题划分成若干同类子问题后,分别解决后,再把结果合并起来

关键点:
原问题和各个子问题都是重复的（同类的）--递归定义
除了向下递归"问题",还要向上合并"结果"
分治算法,一般用递归实现
```
[Pow(x,n)](https://leetcode.cn/problems/powx-n/description/)<br/>
```
n为偶数:Pow(x,n) = Pow(x,n/2) * Pow(x,n/2)
n为奇数:Pow(x,n) = Pow(x,n/2) * Pow(x,n/2) * x
递归边界: Pow(x,1) = x
O(log n)
```
[括号生成](https://leetcode.cn/problems/generate-parentheses/description/)<br/>
分治划分子问题的标准：不重不漏

