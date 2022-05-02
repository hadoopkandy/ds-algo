package com.kandy.algorithm.week02;

/**
 * 53.最大子数组和
 */
public class LC53最大子数组和 {
    public int maxSubArray(int[] nums) {
        //定义之前和、最大和
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            //当前和=之前和+当前值，比较当前和当前值最大值，更新答案
            pre = Math.max(pre + x, x);
            //更新最大和
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }

    public class Status {
        //lSum 表示 [l,r][l,r] 内以 l 为左端点的最大子段和
        //rSum 表示 [l,r][l,r] 内以 r 为右端点的最大子段和
        //mSum 表示 [l,r][l,r] 内的最大子段和
        //iSum 表示 [l,r][l,r] 的区间和
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public int maxSubArray2(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] a, int l, int r) {
        //求解
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        //划分
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        //合并
        return merge(lSub, rSub);
    }

    //以 l=-2 r=1 举例 合并后iSum =-2 +1 =-1  lSum= max(-2,-2+1)=-1 rSum=max(1,1+(-2))=1  mSum=max(max(-2,1),-2+1)=1
    public Status merge(Status l, Status r) {
        int iSum = l.iSum + r.iSum; //左子区间的iSum + 右子区间的iSum
        int lSum = Math.max(l.lSum, l.iSum + r.lSum); //要么等于左子区间的lSum 要么等于左子区间的iSum + 右子区间的lSum,两者取最大
        int rSum = Math.max(r.rSum, r.iSum + l.rSum); //要么等于右子区间的rSum，要么等于右子区间的Sum + 左子区间的rSum,两者取最大
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

    public static void main(String[] args) {
        LC53最大子数组和 code = new LC53最大子数组和();
        System.out.println(code.maxSubArray2(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
    }
}
