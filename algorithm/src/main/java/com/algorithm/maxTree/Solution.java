package com.algorithm.maxTree;


public class Solution {
    public static void main(String[] args) {
        int[] arr = {3,2,1,6,0,5};
        Solution solution = new Solution();
        TreeNode treeNode = solution.constructMaximumBinaryTree(arr);
        System.out.println(treeNode);
    }


    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    TreeNode build (int[] nums, int lo, int hi) {
        if (lo > hi) {
            return null;
        }

        //找出数组中最大值
        int maxVal = Integer.MIN_VALUE, index = -1;
        for (int i = lo; i <= hi; i++) {
            if (maxVal < nums[i]) {
                maxVal = nums[i];
                index = i;
            }
        }

        //创建Node 节点
        TreeNode root = new TreeNode(maxVal);

        //递归调用，构建左右节点
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);

        return root;
    }
}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
