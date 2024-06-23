// Time Complexity : O(NlogN), for traversing and sorting the column and then list based on row.
// Space Complexity : O(n) 
// Did this code successfully run on Leetcode : yes
//Approach : subtract 1 for depth on left and add 1 for depth on right. Do bucket sort on the range of given depths to output the result.


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
    List<List<Integer>> result;
    Map<Integer,List<Integer>> map;
    public List<List<Integer>> verticalOrder(TreeNode root) {
        result = new ArrayList<>();
        map = new HashMap<>();

        if(root == null) return result;
        
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();

        q.add(root);
        colQueue.add(0);
        int min = 0;
        int max = 0;

        while(!q.isEmpty()){
            TreeNode curr = q.poll();
            int depth = colQueue.poll();

            if(!map.containsKey(depth)){
                map.put(depth, new ArrayList<>());
            }

            map.get(depth).add(curr.val);

            if(curr.left != null){
                q.add(curr.left);
                colQueue.add(depth-1);
                min = Math.min(min, depth-1);
            }

            if(curr.right != null){
                q.add(curr.right);
                colQueue.add(depth+1);
                max = Math.max(max, depth+1);
            }
        }
        //bucket sort
        for(int i = min ; i <= max ; i++){
            result.add(map.get(i));
        }

        return result;
    
    }

}

//DFS
// Time Complexity : O(n)
// Space Complexity : O(n) 
// Did this code successfully run on Leetcode : yes
//Approach : subtract 1 for depth on left and add 1 for depth on right. Do bucket sort on the range of given depths to output the result.
// Also maintain both row and column in dfs such that it can be sorted later as per column and then row.

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
    List<List<Integer>> result;
    Map<Integer, List<Pair<Integer, Integer>>> map;
    int min = 0;
    int max = 0;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        result = new ArrayList<>();
        map = new HashMap<>();

        if(root == null) return result;
        dfs(root, 0, 0);
        // System.out.println(map);

        for(int i = min ; i <= max;  i++){
            // sorting based on row because we want lower depth elements before 
            Collections.sort(map.get(i), new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                    return p1.getKey() - p2.getKey();
                }
            });

            List<Integer> sortedList = new ArrayList<>();
            for(Pair<Integer, Integer> pair : map.get(i)){
                sortedList.add(pair.getValue());
            }
            result.add(sortedList);
        }
        return result;
    }

    private void dfs(TreeNode node , int column, int row){
        //base
        if(node == null){
            return;
        }
        min = Math.min(min, column);
        max = Math.max(max, column);

        //logic
        if(!map.containsKey(column)){
            map.put(column, new ArrayList<>());
        }
        map.get(column).add(new Pair(row, node.val));

        dfs(node.left , column-1 , row+1);
        dfs(node.right , column+1 , row+1);
    }
}