##### 贪心算法
```
贪心算法（Greedy Algorithm）是一种
1.在每一步选择中都采取在当前状态下的最优决策(局部最优)
2.并希望由此导致的最终结果也是全局最优的算法

贪心算法的证明：
反证法
数学归纳法
决策包容性
决策范围拓展
邻项交换
```
[柠檬水找零](https://leetcode.cn/problems/lemonade-change/description/)<br/>
```
贪心算法的证明-决策包容性
"零钱兑换"类问题在面值互相构成倍数时,贪心算法成立
在本题中,面值为[5,10,20]
如果能用1个10块找零,那用2个5块必然也可以
也就是说,做出"用10块找零"这个决策,未来的可能性包含了"用2个5块找零"以后未来的可能性--决策包容性
所以本题可以贪心:优先使用面值较大的找零
```
[分发饼干](https://leetcode.cn/problems/assign-cookies/description/)<br/>
```
贪心算法的证明-决策包容性
两种思路：
一块饼干(尺寸s[j]发给谁?发给满足g[i]<=s[j]的最大的g[i] (刚刚好能满足的孩子))
一个孩子(胃口g[i] 吃哪块饼干?吃满足s[j]>=g[i]的最小s[j],不存在就别吃了)
通俗点讲就是小饼干给小孩子,大饼干给大孩子,不能满足就不给了
决策包容性:一块饼干总是想要满足一个孩子的,满足胃口更大的孩子,未来的可能性包含了满足胃口更小孩子的可能性

代码实现思路：
1.对于一个饼干s[j],找到<=s[j]的最大的g[i],把g排好序,在g中找s[j]前驱
2.把饼干和孩子排序,代码更容易实现
```
[买卖股票的最佳时机II](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/description/)<br/>
```
贪心算法的证明-决策范围拓展
这是一个预言家的炒股状态--知道未来每天都价格
当前持有股票,卖不卖?往后看一天,明天涨那肯定不卖,明天跌那肯定卖啊!
当前没有股票,买不买?往后看一天,明天涨那肯定买,明天跌那肯定先不买!
最后就是完美结果--获得所有prices[i]-prices[i-1]>0区间的收益
```
[跳跃游戏II](https://leetcode.cn/problems/jump-game-ii/)<br/>
```
贪心算法的证明-决策范围拓展
看一下往后跳两步的所有可能性
贪心策略:若a 能到b1,b2,b3 ,而b1,b2,b3能到最远的位置分别是 c1,c2,c3,那应该从 a 跳到c1,c2,c3中最大的b
决策包容性:同样都是跳 1 步,从a跳到"能跳得更远"的b,未来的可达集合包含了跳到其他b的可达集合,所以这个局部最优决策是正确的。
```
[完成所有任务的最少初始能量](https://leetcode.cn/problems/minimum-initial-energy-to-finish-tasks/description/)<br/>
```
贪心算法的证明-邻项交换
 贪心策略：actual - minimum 升序排序,以此顺序完成任务
 策略说明:actual越小越往前排  minimum越大(减的越多,整体越小)越往前排，以此顺序完成任务
 先做门槛高的反例：[5,8] [1,7]  门槛高的具有先做的趋势，实际耗费小的具有先做的趋势

 排序后顺序  1,7   5,8   初始能量 max(7, 1 + 8) = 9
 排序后顺序  5,8   1,7   初始能量 max(8,5 + 7) = 12
 排序后顺序  1,12  5,8   初始能量 max(12,1 + 8) = 12

 证明思路：
 假设一共有n个任务，令第i个任务为p，第i+1个任务为q，假设完成第i+2至n个任务需要的能量为s
 倒序考虑(以q点的初始能量为例,要么本身门槛比较高 minimum[q]，要么耗费比较大 s + actual[q],两者取max)
 先完成p后q所需初始能量为: max( max(minimum[q],s + actual[q]) + actual[p], minimum[p])
 先完成q后p所需初始能量为: max( max(minimum[p],s + actual[p]) + actual[q], minimum[q])
 若先做p比较优,则应满足(拆括号,消去一样的项s + actual[q] + actual[p])
 max(minimum[q]+ actual[p], minimum[p]) < max(minimum[p]+actual[q], minimum[q])

 标记 A:minimum[q]+ actual[p]  C:minimum[p]  B:minimum[p]+actual[q]  D: minimum[q]
 max(A,C) < max(B,D) A必大于D  B 必大于C,所以只需要比较 AB
 因为必定有minimum[q] + actual[p] > minimum[q]
 所以上式等价于 minimum[q] + actual[p] < minimum[p] + actual[q]
 即 actual[p] - minimum[p] < actual[q] - minimum[q]
```

##### 动态规划
```
 动态规划(DP)：是一种对问题的状态空间，进行分阶段、有顺序、不重复、决策性遍历的算法
   分阶段（例如：小的amout的先算，算好之后就是记下来就是最优解）
   有顺序（例如i [1,amout]）
   不重复：记忆化只算一次
   决策性：三者取min
 
 动态规划的前提与关键：
   重叠子问题：与递归、分治一样，要具有同类子问题，用若干维状态表示
   最优子结构：状态对应着一个最优化目标，并且最优化目标之间具有推导关系
   无后效性：问题的状态空间是一张有向无环图（可按一定的顺序遍历求解）
 
 动态规划三要素：阶段、状态、决策
 
 标准题解：
   状态转移方程
   边界
   目标
   时间复杂度
```

[零钱兑换](https://leetcode.cn/problems/coin-change/description/)<br/>
```
兑换n元的最少硬币枚数: opt(n)=min(opt(n-1),opt(n-9),opt(n-10))+1

状态转移方程：
  opt[i]表示凑成金额i所需的最少硬币数
  opt[i] = Math.min(opt[i], opt[i - coins[j]] + 1)
  
递推实现
记忆化搜索(递归实现)
```
[不同路径II](https://leetcode.cn/problems/unique-paths-ii/description/)<br/>
```
Top-down
    f(i,j) 表示从Start到(i,j)的路径数  （从上或左来）
    如果（i，j）是空地 ,f(i,j)=f(i-1,j)+f(i,j-1) 否则f(i,j)=0  
Bottom-up
    f(i,j) 表示从(i,j)到End的路径数 每步只能往下或往右走
    如果（i，j）是空地 ,f(i,j)=f(i+1,j)+f(i,j+1) 否则f(i,j)=0
```
[最长公共子序列](https://leetcode.cn/problems/longest-common-subsequence/description/)<br/>
```
  f(i,j) 表示text1的前i个字符和text2的前j个字符能组成的LCS长度
   如果text1[i] = text2[j]   f(i,j) = f(i-1,j-1)+1
   如果text1[i] != text2[j]  f(i,j) = max(f(i-1,j),f(i,j-1))
```
[最长递增子序列](https://leetcode.cn/problems/longest-increasing-subsequence/description/)<br/>
```
  f(i) 表示前i个数构成的以a[i]为结尾的最长上升子序列的长度
  f[i] = Math.max(f[i], f[j] + 1)  j<i
  
  状态中何时需要"包含结尾"?
  当结尾参与判定条件,例如LIS中a[j]<a[i],此处子序和要"连续"
```
[最大子数组和](https://leetcode.cn/problems/maximum-subarray/description/)<br/>
```
   f(i)表示以i为结尾的最大子序列和
   f[i] = max(f[i - 1] + nums[i], nums[i])
```
[乘积最大子数组](https://leetcode.cn/problems/maximum-product-subarray/description/)<br/>
```
   𝑓[𝑖] 表示以 𝑖 为结尾的乘积最大子数组
   𝑓[𝑖] = max 𝑓 𝑖 − 1 ∗ 𝑛𝑢𝑚𝑠[i] , 𝑛𝑢𝑚𝑠[𝑖]—— 这样还对吗？
   请注意,"代表"之间要有推导关系，才满足最优子结构！
   当 𝑛𝑢𝑚𝑠 𝑖 是负数的时候，max 还能推导出 max 吗？
   本题中,max和min一起作为代表,才能满足最优子结构!

   fmax[i] fmin[i]表示以i结尾的乘积最大、最小子数组
   fmax[i] = max(fmax[i - 1] * nums[i], fmin[i - 1] * nums[i], nums[i])
   fmin[i] = min(fmax[i - 1] * nums[i], fmin[i - 1] * nums[i], nums[i])
   fmin[i - 1] 越小 *  nums[i] 可能成为最大乘积
```