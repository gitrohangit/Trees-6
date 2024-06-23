// Time Complexity : O(n)
// Space Complexity : O(n) 
// Did this code successfully run on Leetcode : yes
//Approach : Serialize in BFS approach and deserialize by reversing the steps
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            TreeNode curr = q.poll();
            if(curr != null){
                sb.append(curr.val);
                q.add(curr.left);
                q.add(curr.right);
            }
            else{
                sb.append("#"); // null value
            }
            sb.append(" ");
        }
        // System.out.println(sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
        if(data.length() == 0) return null;

        String[] strArr = data.split(" ");
        Queue<TreeNode> q = new LinkedList<>();

        int idx = 0;
        TreeNode root = new TreeNode(Integer.parseInt(strArr[idx]));
        q.add(root);
        idx++;

        while(idx != strArr.length){
            TreeNode curr = q.poll();

            if(!strArr[idx].equals("#")){
                curr.left = new TreeNode(Integer.parseInt(strArr[idx]));
                q.add(curr.left);
            }
            idx++;
            if(!strArr[idx].equals("#")){
                curr.right = new TreeNode(Integer.parseInt(strArr[idx]));
                q.add(curr.right);
            }
            idx++;

        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));


//Approach 2:
// Time Complexity : O(n)
// Space Complexity : O(n) 
// Did this code successfully run on Leetcode : yes
//Approach : Serialize and deserialize in DFS pre order manner
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();

        if(root == null) return sb.toString();

        serializeHelper(root,sb);
        System.out.println(sb.toString());
        return sb.toString();
    }

    private void serializeHelper(TreeNode node , StringBuilder sb){
        //base
        if(node == null){
            sb.append("#");
            sb.append(" ");
            return;
        }
        //logic - pre order
        sb.append(node.val);
        sb.append(" ");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);

    }

    int idx = 0;
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0) return null;

        String[] arr = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(arr[idx]));
        idx++;

        deserializeHelper(arr,root);

        return root;
    }

    private void deserializeHelper(String[] arr, TreeNode curr){
        //base
        if(curr == null){
            return;
        }

        //logic

        if(!arr[idx].equals("#")){
            curr.left = new TreeNode(Integer.parseInt(arr[idx])); 
        }
        idx++;
        deserializeHelper(arr, curr.left);

        if(!arr[idx].equals("#")){
            curr.right = new TreeNode(Integer.parseInt(arr[idx]));
        }
        idx++;
        deserializeHelper(arr, curr.right); 

    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));