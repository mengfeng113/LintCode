/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     * @param root the root of binary tree
     * @param target an integer
     * @return all valid paths
     */
    private List<List<Integer>> ans;
    private ArrayList<Integer> path;
    
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        ans = new ArrayList();
        path = new ArrayList();
        helper(root, target);
        return ans;
    }
    private void helper(TreeNode node, int target){
        if (node == null) return;
        int cur = node.val;
        path.add(cur);
        if (node.left == null && node.right == null){
            if (target == cur){
                ans.add(new ArrayList<Integer>(path));
            }
        } else {
            helper(node.left, target - cur);
            helper(node.right, target - cur);
        }
        path.remove(path.size() - 1);
    }
}