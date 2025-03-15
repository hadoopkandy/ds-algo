##### 树的遍历
```
先序、中序、后序一般用递归来求
树的先序遍历又称树的深度优先遍历

层次序一般借助队列来求
树的层序遍历又称树的广度优先遍历
```
[二叉树的前序遍历](../../src/main/java/com/kandy/algorithm/week03/L01_C144二叉树的前序遍历.java)<br/>
```
方法一：递归
方法二：迭代 借助Stack
   前序遍历顺序：根-左-右，入栈顺序：根-右-左
```
[二叉树的中序遍历](https://leetcode.cn/problems/binary-tree-inorder-traversal/description/)<br/>
```
方法一：递归
方法二：迭代 借助Stack
   中序遍历顺序: 左-根-右 入栈顺序：  一路到到左 -> 弹栈 -> 右子树
```
[二叉树的后序遍历](../../src/main/java/com/kandy/algorithm/week03/L03_C145二叉树的后序遍历.java)<br/>
```
方法一：递归
方法二：迭代 借助Stack
   后序遍历顺序 左-右-根 入栈顺序：根-左-右 出栈顺序：根-右-左， 最后翻转结果
```
[二叉树的层序遍历](../../src/main/java/com/kandy/algorithm/week03/L04_C102二叉树的层序遍历.java)<br/>
```
方法一：DFS--递归方式
方法二：BFS--迭代方式--借助队列 
```
[N叉树的前序遍历](https://leetcode.cn/problems/n-ary-tree-preorder-traversal/description/)<br/>
```
方法一：递归
方法二：迭代 借助Stack
   从右往左放栈,保证取的时候是从左往右
```
[N叉树的层序遍历](https://leetcode.cn/problems/n-ary-tree-level-order-traversal/)<br/>
```
二叉树层序遍历类似
方法一：DFS--递归方式
方法二：BFS--迭代方式--借助队列 
```
[二叉树的序列化与反序列化](https://leetcode.cn/problems/serialize-and-deserialize-binary-tree/description/)<br/>
[从前序与中序遍历序列构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/description/)<br/>
[树的直径](https://leetcode.cn/problems/tree-diameter/description/)<br/>
```
两次深度优先遍历
  第一次从任意一个点出发,找到距离它最远的点p
  第二次从p出发,找到距离它最远的点q
  连接p,q的路径即为树的直径
 
关键点：出边数组存储图、基于queue的广度优先遍历记录深度
```
[二叉树的最近公共祖先](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/description/)<br/>
```
方法一：向上标记法
先求出父节点,然后用向上标记法
 p向上一直到root标红色
 q向上,第一次遇到红色时,就找到了LCA
方法一：递归DFS
      包含p、q结点的最小子树,dfs最深的一次
```

##### 链表、树、图的关系
```
链表是特殊化的树
树是特殊化的图
  N个点 N-1条边的连通无向图-树
  N个点 N条边的连通无向图-基环树
```

##### 图的存储
```
邻接矩阵
出边数组
邻接表
```

##### 图的遍历
```
深度优先遍历 
  划分连通块
广度优先遍历
  拓扑排序  
```
[冗余连接](https://leetcode.cn/problems/redundant-connection/description/)<br/>
```
无向图环的检测:
出边数组、无向图深度优先遍历找环、visit数组，避免重复访问
```
[冗余连接II](https://leetcode.cn/problems/redundant-connection-ii/description/)<br/>
```
有向图环检测方法：通过color数组进行DFS遍历
0尚未访问 1正在递归访问中 2访问完毕
找到1正在递归访问中且重复访问的，说明有环

有向树的性质，如果是有向树的话，只有根节点入度为0，其他节点入度都为1
（因为该树除了根节点之外的每一个节点都有且只有一个父节点，而根节点没有父节点）
情况一：如果我们找到入度为2的点，那么删一条指向该节点的边
情况二: 只能删特定的一条边（有入度为2的点，且有环）
情况三： 如果没有入度为2的点，说明 图中有环了（注意是有向环）

基于上面思考，整体思路如下：
1.将引起入度为2 和环的边依次加入到Stack里
2.遍历该Stack，如果删除后dfs遍历完，没有环了，也没有入度为2的点，就是要的答案
```
[课程表](https://leetcode.cn/problems/course-schedule/)<br/>
[课程表II](https://leetcode.cn/problems/course-schedule-ii/description/)<br/>
```
关键点：出边数组、入度数组、拓扑排序
拓扑排序第一步，零入度点入队
第二步，扩展每个点 周围的点入度减1
第三步，入度为0，表示可以入队了
```

##### 堆(Heap)
```
 堆(Heap)是一种高效维护集合中最大或最小元素的数据结构。
 
 大根堆:根节点最大的堆,用于维护和查询max。 
 小根堆:根节点最小的堆,用于维护和查询min。
 
 堆是一颗树,并且满足堆性质(Heap property)
 大根堆任意结点的关键码>=它所有子结点的关键码（父>=子）
 小根堆任意结点的关键码>=它所有子结点的关键码（父<=子）
```
##### 二叉堆(Binary Heap)
```
二叉堆是堆的一种简易实现,本质上是一颗满足堆性质的完全二叉树
常见操作:
  建堆(build) : O(N)
  查询最值(get max/min) ：O(1)
  插入(insert) : O(logN)
  取出最值（delete max/min）:O(logN)
```
##### 二叉堆的实现
```
二叉堆一般使用一个一维数组来存储,利用完全二叉树的结点编号特性
假设第一个元素存储在索引(下标) 为1 位置的话
  索引为p的结点的左孩子的索引为 p*2
  索引为p的结点的右孩子的索引为 p*2 + 1
  索引为p的结点的父亲的索引为 p/2 (下取整)

假设第一个元素存储在索引(下标) 为0 位置的话
  索引为p的结点的左孩子的索引为 p*2 + 1 
  索引为p的结点的右孩子的索引为 p*2 + 2
  索引为p的结点的父亲的索引为 (p-1)/2 (下取整)  
  
插入（insert）
 新元素一律插入到数组heap的尾部，设插入到了索引p的位置
 然后向上进行一次调整（Heapify Up）
  若已到达根,停止
  若满足堆性质(heap[p] <= heap[p/2]),停止
  否则交换heap[p] 和heap[p/2],令p=p/2 ,继续调整
 O(logN)
 
取出堆顶（extract/delete max）
把堆顶（heap[1]）与堆尾（heap[n]）交换,删除堆尾（数组最后一个元素）
然后从根向下进行一次调整(Heapify Down)
 每次与左、右结点中较大的一个比较,检查堆性质，不满足则交换
 注意判断子结点是否存在
O(logN) 
```

##### 优先队列（Priority Queue）
```
二叉堆是优先队列（Priority Queue）一种简单、常见的实现，但不是最优实现
理论上二叉堆可以支持O(logN)删除任意元素，只需要
 定位该元素在堆中的结点p（可以通过在数值与索引之间建立映射得到）
 与堆尾交换,删除堆尾
 从p向上、向下各进行一次调整
 
不过优先队列,并没有提供这个方法
在各语言内置的库中,需要支持删除任意元素时,一般使用有序集合等基于平衡二叉搜索树的实现
```
[合并K个升序链表](https://leetcode.cn/problems/merge-k-sorted-lists/description/)<br/>
```
思路一：分钟治合并,递归方式实现
思路二: 优先队列实现（java PriorityQueue默认小顶堆）
   1.将lists 中所有节点放入优先队列中
   2.迭代优先队列，每次出队的最小元素节点合并到新链表末尾
   3.迭代的链表节点不是未节点，取下一个节点继续放入优先队列中
思路三：自己实现二叉堆
    用动态数组实现，插入到尾部，从最后一个位置Heapify Up
    取出堆顶时，把堆顶heap[0]与堆尾heap[n-1]交换，删除堆尾（数组最后一个元素）,从堆顶HeapifyDown
```
[滑动窗口最大值](https://leetcode.cn/problems/sliding-window-maximum/description/)<br/>
```
对比单调队列和堆的解法
懒惰删除法（把堆顶的删除操作推迟到对答案有影响时）
 指的是需要从堆中删除一个元素（不一定是最大值）时,不直接删除,而是打上删除标记（soft delete）
 等未来它作为最大值被取出时,再判断它是否已经被标记,若是则抛弃它,取下一个最大值
 "懒惰"的含义--只要要删除的元素还不是最大值,在堆里多待一会儿也无妨,未来等它影响最大值正确性的时候再说吧
```

##### 二叉搜索树Binary Search Tree
```
二叉搜索树(Binary Search Tree)是一棵满足如下性质(BST)的二叉树：
任意结点的关键码>=它左子树中所有结点的关键码
任意结点的关键码<=它右子树中所有结点的关键码
根据以上性质,二叉搜索树的中序遍历必然为一个有序序列

BST-建立
为了避免越界,减少边界情况的特殊判断,一般在BST中额外插入两个保护结点
一个关键码为正无穷（一个很大的正整数）
一个关键码为负无穷
仅由这两个结点构成的BST就是一棵初始的空BST

BST-检索
检索关键码val是否存在
从根开始递归查找
若当前结点的关键码等于val,则已经找到
若关键码大于val,递归检索左子树（或不存在）
若关键码小于val,递归检索右子树（或不存在）

BST-插入
插入val与检索val的过程类似
 若检索发现存在,则放弃插入（或者把val对应结点的计数+1,视要求而定）
 若检索发现不存在(子树为空),直接在对应位置新建关键码为val的结点

BST-求前驱动/后继
前驱:BST中小于val的最大结点
后继:BST中大于val的最小结点
求前驱和后继也是基于检索的,先检索val
以后继为例:
 如果检索到了val,并且val存在右子树,则在右子树中一直往左走到底
 否则说明没找到val或者val没有右子树,此时后继就在检索过程经过的结点中
 （即当前结点的所有祖先结点,可以拿一个变量顺便求一下）

BST-删除
从BST中删除关键码为val的结点,可以基于检索+求后继实现
首先检索val
如果val只有一棵子树,直接删除val,把子树和父结点相连就行了
如果有两棵子树,需要找到后继,先删除后继,再用后继结点代替val的位置
（因为后继是右子树一直往左走到底,所以它最多只会有一棵子树）

查询/插入/求前驱/求后继删除操作的时间复杂度：
随机数据期望O(logN)
在非随机数据上,BST容易退化为O(N),一般都需要结合旋转操作来进行平衡
```
[二叉搜索树中插入操作](https://leetcode.cn/problems/insert-into-a-binary-search-tree/description/)<br/>
[后继者](https://leetcode.cn/problems/successor-lcci/description/)<br/>
```
case1：检索到val且右子树存在，右子树一路向左
case2：当后继存在于经过的点中（找到一个>val的最小点）
```
[删除二叉搜索树中的结点](https://leetcode.cn/problems/delete-node-in-a-bst/description/)<br/>
[把二叉搜索树转换为累加树](https://leetcode.cn/problems/convert-bst-to-greater-tree/description/)<br/>