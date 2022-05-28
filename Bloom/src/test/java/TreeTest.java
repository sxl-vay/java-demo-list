import top.boking.POJO.TreeNode;
import top.boking.utils.TreeUtil;

public class TreeTest {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        treeNode1.setLeftNode(treeNode2);
        treeNode2.setRightNode(treeNode3);
        treeNode3.setRightNode(treeNode4);
        treeNode4.setRightNode(treeNode5);
        treeNode5.setRightNode(treeNode6);
        treeNode6.setRightNode(treeNode7);
        treeNode6.setLeftNode(treeNode8);
        int treeHigh = TreeUtil.getTreeHigh(treeNode1);
        System.out.println("treeHigh = " + treeHigh);
        TreeNode leftNode = treeNode1.getLeftNode();
        int var = leftNode.getVar();
        System.out.println("var = " + var);
    }




}
