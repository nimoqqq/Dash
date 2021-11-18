package com.algorithm.invertTree;

/**
 * 226. 翻转二叉树
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        swap(root);

        if (root.left != null) {
            invertTree(root.left);
        }
        if (root.right != null) {
            invertTree(root.right);
        }

        return root;
    }


    private void swap(TreeNode node) {
        TreeNode right = node.right;
        node.right = node.left;
        node.left = right;
    }
}
