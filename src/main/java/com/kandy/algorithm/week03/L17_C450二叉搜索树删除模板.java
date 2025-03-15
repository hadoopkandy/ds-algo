package com.kandy.algorithm.week03;

import com.kandy.algorithm.leetcode.TreeNode;

/**
 * 从BST中删除关键码为val的结点，可以基于检索+求后继实现
 * 首先检索val
 * 1.如果val只有一棵树，直接删除val，把子树和父结点相连就行了
 * 2.如果有两棵子树，需要找到后继，先删除后继，再用后继结点，代替val的位置
 * （因为后继的右子树一直往左走到底，所以它最多只会有一棵子树，容易删，且不会破坏BST性质）
 */
public class L17_C450二叉搜索树删除模板 {
    // 在以root为根的子树中删除key，返回新的根
    public TreeNode deleteNode(TreeNode root, int key) {
        //找不到，就不管了
        if (root == null) return null;
        //找到，就执行删除
        if (root.val == key) {
            if (root.left == null) return root.right; // 没有左子树,让right代替root的位置
            if (root.right == null) return root.left; // 没有右子树,让left代替root的位置
            TreeNode next = root.right;
            while (next.left != null) next = next.left; // 找后继：右子树一路向左
            //在右子树中删后继，后继肯定没有左孩子，直接让右孩子代替后继
            root.right = deleteNode(root.right, next.val);
            //后继的值代替root,root是待删除节点
            root.val = next.val;
            return root;
        }
        //小于，往左
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    /*
      非递归方式，记录被删除节点及父节点：
      第一种情况是，如果要删除的节点没有子节点，我们只需要直接将父节点中，指向要删除节点的指针置为 null
      第二种情况是，如果要删除的节点只有一个子节点（只有左子节点或者右子节点），我们只需要更新父节点中，指向要删除节点的指针，让它指向要删除节点的子节点就可以了。
      第三种情况是，如果要删除的节点有两个子节点，这就比较复杂了。我们需要找到这个节点的右子树中的最小节点，把它替换到要删除的节点上。
      然后再删除掉这个最小节点，因为最小节点肯定没有左子节点（如果有左子结点，那就不是最小节点了），
      所以，我们可以应用上面两条规则来删除这个最小节点。
     */
    public TreeNode deleteNode2(TreeNode root, int key) {
        TreeNode p = root; // p指向要删除的节点，初始化指向根节点
        TreeNode pp = null; // pp记录的是p的父节点
        while (p != null && p.val != key) {
            pp = p;
            if (key > p.val) p = p.right;
            else p = p.left;
        }
        if (p == null) return root; // 没有找到

        // 要删除的节点有两个子节点
        if (p.left != null && p.right != null) { // 查找右子树中最小节点
            TreeNode minP = p.right;
            TreeNode minPP = p; // minPP表示minP的父节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.val = minP.val; // 将minP的数据替换到p中
            p = minP; // 下面就变成了删除minP了
            pp = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        TreeNode child; // p的子节点
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) {
            root = child;
        } // 删除的是根节点
        else if (pp.left == p) pp.left = child;
        else pp.right = child;
        return root;
    }

    public static void main(String[] args) {
        /*
        deleteNode(5,5)
        找后继：右子树一路向左 找到next = 6
        delete(9,6) => 9.left = 8
        root.val = 6
         */
        L17_C450二叉搜索树删除模板 solution = new L17_C450二叉搜索树删除模板();
        TreeNode five = new TreeNode(5);
        TreeNode two = new TreeNode(2);
        TreeNode nine = new TreeNode(9);
        TreeNode int_min = new TreeNode(Integer.MIN_VALUE);
        TreeNode three = new TreeNode(3);
        TreeNode six = new TreeNode(6);
        TreeNode int_max = new TreeNode(Integer.MAX_VALUE);
        TreeNode one = new TreeNode(1);
        TreeNode eight = new TreeNode(8);
        five.left = two;
        five.right = nine;
        two.left = int_min;
        two.right = three;
        nine.left = six;
        nine.right = int_max;
        int_min.right = one;
        six.right = eight;
        System.out.println(solution.deleteNode(five, 5).val);

//        TreeNode five = new TreeNode(5);
//        TreeNode three = new TreeNode(3);
//        TreeNode six = new TreeNode(6);
//        TreeNode two = new TreeNode(2);
//        TreeNode four = new TreeNode(4);
//        TreeNode seven = new TreeNode(7);
//        five.left = three;
//        five.right = six;
//        three.left = two;
//        three.right = four;
//        six.right =seven;
//        System.out.println(solution.deleteNode(five,3).val);
    }

}
