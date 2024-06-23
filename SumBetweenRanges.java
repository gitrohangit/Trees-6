//Post Order Iterative solution
// Time Complexity : O(n)
// Space Complexity : O(n) 
// Did this code successfully run on Leetcode : yes

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        Stack<TreeNode> st = new Stack<>();
        TreeNode prev = null;
        st.push(root);
        int res = 0;
        while(!st.isEmpty()){
            TreeNode curr = st.peek();
            //downward motion
            if(prev == null || prev.left == curr || prev.right == curr){
                if(curr.left != null){ //left
                    st.push(curr.left);
                }
                else if(curr.right != null){ //right
                    st.push(curr.right);
                }
                else{ //root
                    root = st.pop();
                    if(root.val >= low && root.val <= high){
                        res += root.val;
                    }
                }
            }
            else if(curr.left == prev){
                if(curr.right != null){ //right
                    st.push(curr.right);
                }
                else{ //root
                    root = st.pop();
                    if(root.val >= low && root.val <= high){
                        res += root.val;
                    }
                }
            }
            else //root
            { 
                root = st.pop();
                if(root.val >= low && root.val <= high){
                    res += root.val;
                }
            }

            prev = curr;
        }
        return res;
    }
}