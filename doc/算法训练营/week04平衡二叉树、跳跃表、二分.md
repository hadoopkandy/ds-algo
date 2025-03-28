##### AVL树(平衡二叉搜索树)
```
平衡因子 Balance Factor
   一个结点的左子树的高度减去它的右子树的高度

AVL树
  任意结点的平衡因子的绝对值都不超过1,即balance factor={-1,0,1}
  每个结点需要保存：原始数据、左子结点、右子结点、子树高度
  
AVL树在插入、删除时,沿途更新结点的高度值
当平衡因子的绝对值大于1时,触发树结构的修正,通过旋转操作来进行平衡

旋转场景一：LL
左左子树：右旋  
旋转场景二：RR
右右子树:左旋
旋转场景三:LR
左右子树:先左旋、再右旋
旋转场景四:RL 
右左子树：先右旋、再左旋
```

##### 红黑树
```
红黑树（Red-Black Tree）是一种近似平衡的二叉搜索树
 从根到叶子的最长路径<= 2*最短路径（简记：高度差在2倍以内）

规则：
 每个结点要么是红色,要么是黑色
 根节点是黑色
 最底层视作有一层叶子结点,是黑色的
 不能有2个相邻的红色结点
 从任一结点到其每个叶子的所有路径都包含相同数目的黑色结点
 
规则被打破时,通过变色或旋转操作来修正
有非常多的情况需要讨论,这里就不再讲了

相比AVL树,红黑树插入删除更快(旋转少),更省空间（颜色vs平衡因子）
查询稍慢（不如AVL树更加平衡）
红黑树是许多语言中有序集合,有序映射（例如c++ set,map）的内部实现方式
```

##### 跳表
```
跳表（Skip List）是对元素的链表的优化,对标的是平衡树和二分查找
  二分查找：可以在数组上O(logN)查询,不可修改序列（不能用于链表）
  平衡树：支持高效的查询、插入、删除,但比较复杂,不容易实现

跳表是一种查询、插入、删除都是O(logN)的数据结构,其特点是原理简单、容易实现、方便扩展、效率优秀
在Redis、LevelDB等热门项目中用于代替平衡树

链表插入、删除都是O(1),但是查询很慢 O(N)
跳表的核心思想：如何提高有序链表的查询效率？  
```

[设计跳表](https://leetcode.cn/problems/design-skiplist/description/)<br/>
```
  search：从跳表的当前的最大层数 level 层开始查找，找到离当前节点最近的结点（最后一个小于value的点）,如果它的next value 等于value 直接返回
  add:   从跳表的当前的最大层数 level 层开始查找，找到离当前节点最近的结点（最后一个小于value的点）,如果当前层< randomLevel,插入新节点。
         如果随机出来的层数>当前层数，那第i层的head直接指向newNode,不需要有updateNode
  erase： 从跳表的当前的最大层数 level 层开始查找,跟search类似，如果找到就删掉它。
```
[普通平衡树](https://www.acwing.com/problem/content/255/)<br/>
[滑动窗口最大值](https://leetcode.cn/problems/sliding-window-maximum/description/)<br/>
[邻值查找](https://www.acwing.com/problem/content/138/)<br/>
```
  TreeSet 常用方法
  higher 后继
  lower 前驱
  first firstKey
  last lastKey
```

##### 二分查找
```
前提：
目标函数具有单调性（单调递增或者递减）
存在上下界面(bounded)
能够通过索引访问(index accessible)
```
[二分查找](https://leetcode.cn/problems/binary-search/description/)<br/>

##### 适用性更广的二分模版(1.1 后继型)
```
查找lower_bound(第一个>=target的数),不存在返回n
int left = 0,right = n; //表示无解
//循环终止于left = right
while (left < right) {
    int mid = (left + right) / 2;
    if (array[mid] >= target) {
        right = mid; //condition satisfied,should be included
       } else {
         left = mid + 1;
       }
  }
return right;  

代码说明：
 1.int left = 0,right = n;
   left = n-1, right = n,这时 mid = n-1
   left = n-2, right = n,这时 mid = n-1
   不会出现越界
 2.代码终止于left = right
 3.查找范围0~n,终止于n表示无解 说明每次都是执行 left = mid + 1
 4.改为array[mid] > target 就是 upper_bound 
```
##### 适用性更广的二分模版(1.2 前驱型)
```
查找最后一个<=target 的数,不存在返回-1
int left = -1,right = n-1; //表示无解
//循环终止于left = right
while (left < right) {
    int mid = (left + right + 1) / 2;
    if (array[mid] <= target) {
        left = mid; //condition satisfied,should be included
       } else {
         right = mid - 1;
       }
  }
return right; 

代码说明： 
 1.(left + right + 1) / 2;为什么要加1往上取整 ？
   [L,R] 当mid = L时 保证while循环终止于R
   [L,R] 当mid = R时 保证while循环终止于L 
 2. 同理如何循环终止于left = right = -1 说明一直在执行right = mid - 1
```

##### 推荐使用1.1 + 1.2
```
 写出正确的二分查找代码"三步走":
 1.写出二分的条件(一般是一个不等式,例如upper_bound:>val的数中最小的)
 2.把条件放到if(...)里,并确定满足条件时要小的去左边(right=mid) 还是要大的去右边(left=mid)
 3.另一半放到else里(left = mid + 1或right = mid -1),如果是后者,求mid时补 +1
 如果题目有无解的情况,上界增加1或者下界减小1,用于表示无解。
```

[寻找旋转排序数组中的最小值](https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/description/)<br/>
```
只要"条件"单调,二分就适用
  旋转排序数组中的最小值,序列本身不单调
  但是"<="结尾这个条件,把序列分成两半,一半不满足(>结尾),一半满足(<=结尾)
可以把"条件满足" 看作1，不满足看作0,这就是一个0/1分段函数,二分查找分界点 

让每个数尝试跟结尾比较 nums[i] <= nums[n-1]
找到满足条件时较小的index
看到0 答案在右边，看到1 答案在自己或者左边
```
[在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/)<br/>
[x的平方根](https://leetcode.cn/problems/sqrtx/description/)<br/>

##### 三分查找
```
三分法用于求单峰函数的极大值(或单谷函数的极小值)
三分法也可用于求函数的局部极大/极小值
要求：函数是分段严格单调递增的/递减的(不能出现一段平的情况)

以求单峰函数f的极大值为例，可定义域[l,r]上取任意两点lmid,rmid
  若f(lmid) <= f(rmid)，则函数必然在lmid处单调递增，极值在[lmid,r]上
  若f(lmid) > f(rmid)，则函数必然在rmid处单调递减，极值在[l,rmid]上
lmid,rmid 可取三等分点
也可取lmid 为二等分点,rmid在lmid稍加一点偏移量
```
[寻找峰值](https://leetcode.cn/problems/find-peak-element/description/)<br/>

##### 二分答案
```
对于一个最优化问题
求解:求一个最优解(最大值/最小值)
判断：给一个解,判断它是否合法(是否能够实现)

"判定"通常要比"求解"简单很多
如果我们有了一个判定算法,那把解空间枚举+判定一遍,就得到解了

当解空间具有单调性时,就可以用二分代替枚举,利用二分+判定的方法快速求出最优解,这种方法称为二分答案法
例如：求解-猜数,判定大了还是小了
低效算法:1到n挨个猜一遍; 高效算法:二分
```

[分割数组的最大值](https://leetcode.cn/problems/split-array-largest-sum/description/)<br/>
```
给定一个非负整数数组nums和一个整数m,你需要将这个数组分成m个非空的连续子数组。
设计一个算法使得这m个子数组各自和的最大值最小。

求解：最小化"m个子数组各自和的最大值"
判定：给一个数值T,"m个子数组各自和的最大值<=T" 是否合法
换一种说法："能否将nums分成m个连续子数组,每组的和<=T"
```
[制作m束花所需的最少天数](https://leetcode.cn/problems/minimum-number-of-days-to-make-m-bouquets/description/)<br/>