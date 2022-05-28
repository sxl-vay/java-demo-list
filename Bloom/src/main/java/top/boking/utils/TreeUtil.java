package top.boking.utils;

import top.boking.POJO.TreeNode;

public class TreeUtil {
    // private TreeNode treeNode;

    public static int getTreeHigh(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        TreeNode rightNode = treeNode.getRightNode();
        TreeNode leftNode = treeNode.getLeftNode();
        int rightMaxDepth = getTreeHigh(rightNode);
        int leftMaxDepth= getTreeHigh(leftNode);
        return Math.max(rightMaxDepth,leftMaxDepth)+1;
    }


}
