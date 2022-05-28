package top.boking.POJO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreeNode {
    private int var;
    private TreeNode leftNode;
    private TreeNode rightNode;

    public TreeNode setLeftNode(TreeNode treeNode) {
        this.leftNode = treeNode;
        return treeNode;
    }

    public TreeNode setRightNode(TreeNode treeNode) {
        this.rightNode = treeNode;
        return treeNode;
    }

    public TreeNode(int var) {
        this.var = var;
    }
}
