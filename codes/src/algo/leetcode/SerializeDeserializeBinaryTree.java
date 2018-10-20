package algo.leetcode;

import algo.depthfirst.TreeNode;

public class SerializeDeserializeBinaryTree {

    public String serialize(TreeNode root) {
        if (root == null)
            return "N";
        return root.val + " " + serialize(root.left) + " " + serialize(root.right);
    }
    // Decodes your encoded data to tree.
    int s = 0;
    public TreeNode deserialize(String data) {
        int e = data.indexOf(" ", s);
        if (e == -1) return null;
        String raw = data.substring(s, e);
        s = e + 1;
        TreeNode node = null;
        if (!raw.equals("N")) {
            node = new TreeNode(Integer.parseInt(raw));
            node.left = deserialize(data);
            node.right = deserialize(data);
        }
        return node;
    }
}
