import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * Represents a node in the binary tree.
 */
class TreeNode {

    // **** class members ****
    int val;
    TreeNode left;
    TreeNode right;

    // **** constructor(s) ****
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

    // **** ****
    @Override
    public String toString() {
        return "" + this.val;
    }
}


/**
 * 
 */
public class NumberVisibleNodes {


    /**
     * Display node values in a binary tree in order traversal.
     * Recursive approach.
     * 
     * !!! NOT PART OF SOLUTION !!!
     */
    static void inOrder(TreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.toString() + " ");
            inOrder(root.right);
        }
    }


    /**
     * This method implements a breadth-first search traversal of a binary tree.
     * This method is iterative.
     * It displays all nodes at each level on a separate line.
     */
    static String bfsTraversal(TreeNode root) {
    
        // **** to generate string ****
        StringBuilder sb = new StringBuilder();
    
        // **** define the current and next queues ****
        List<TreeNode> currentQ = new LinkedList<TreeNode>();
        List<TreeNode> nextQ    = new LinkedList<TreeNode>();
    
        // ***** ****
        boolean allNulls = false;

        // **** prime the queue ****
        currentQ.add(root);

        // **** loop while the queue is not empty ****
        while (!currentQ.isEmpty()) {

            // **** check if the queue only contains null nodes ****
            if (allNulls)
                break;

            // **** remove the next node from the current queue ****
            TreeNode n = currentQ.remove(0);
    
            // **** display the node value ****
            if (n != null)
                sb.append(n.toString() + " ");
            else
                sb.append("null ");
    
            // **** add left and right children to the next queue ****
            if (n != null) {
                if (n.left != null)
                    nextQ.add(n.left);
                else
                    nextQ.add(null);
    
                if (n.right != null)
                    nextQ.add(n.right);
                else
                    nextQ.add(null);
            }
    
            // **** check if the current queue is empty (reached end of level) ****
            if (currentQ.isEmpty()) {
                
                // **** end of current level ****
                sb.append("\n");
    
                // **** check if we have all null nodes in the next queue ****
                allNulls = true;
                for (TreeNode t : nextQ) {
                    if (t != null) {
                        allNulls = false;
                        break;
                    }
                }
    
                // **** point the current to the next queue ****
                currentQ = nextQ;
    
                // **** clear the next queue ****
                nextQ = new LinkedList<TreeNode>();
    
                // **** clear the current queue (all null nodes) ****
                if (allNulls)
                    currentQ = new LinkedList<TreeNode>();
            }
        }
    
        // **** return a string representation of the BT ****
        return sb.toString();
    }


    /**
     * Populate binary tree using values from a first breadth
     * search traversal.
     */
    static TreeNode populateTree(Integer[] arr) {

        // **** sanity checks ****
        if (arr == null || arr.length == 0) {
            return null;
        }
    
        // **** initialization ****
        Queue<TreeNode> treeNodeQ   = new LinkedList<>();
        Queue<Integer> integerQ     = new LinkedList<>();

        // **** populate integer queue ****
        for (int i = 1; i < arr.length; i++) {
            integerQ.offer(arr[i]);
        }
    
        // **** prime tree node queue ****
        TreeNode treeNode = new TreeNode(arr[0]);
        treeNodeQ.offer(treeNode);
    
        // **** ****
        while (!integerQ.isEmpty()){

            // **** ****
            Integer leftVal     = integerQ.isEmpty() ? null : integerQ.poll();
            Integer rightVal    = integerQ.isEmpty() ? null : integerQ.poll();
            TreeNode current    = treeNodeQ.poll();

            // **** ****
            if (leftVal != null) {
                TreeNode left = new TreeNode(leftVal);
                current.left = left;
                treeNodeQ.offer(left);
            }

            // **** ****
            if (rightVal != null){
                TreeNode right = new TreeNode(rightVal);
                current.right = right;
                treeNodeQ.offer(right);
            }
        }

        // **** return binary tree ****
        return treeNode;
    }


    /**
     * Count visible nodes in binary tree.
     * Recursive call.
     * O(n)
     */
    static int visibleNodes(TreeNode root) {

        // **** base condition ****
        if (root == null) return 0;

        // **** recursive call(s) ****
        return Math.max(visibleNodes(root.left) + 1, visibleNodes(root.right) + 1);
    }


    /**
     * Given a binary tree, find its maximum depth.
     * Recursive function O(log(n)).
     */
    static int maxDepth(TreeNode root) {
        
        // **** base condition ****
        if (root == null)
            return 0;

        // **** initialize the BT max depth ****
        int depth = 0;

        // **** visit left node ****
        depth = Math.max(depth, maxDepth(root.left) + 1);

        // **** visit right node ****
        depth = Math.max(depth, maxDepth(root.right) + 1);

        // **** return the max depth ****
        return depth;
    }




    /**
     * 
     */
    static int levelOrderTraversal(TreeNode root) {

        // **** ****
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        Queue<TreeNode> t = new LinkedList<TreeNode>();
        int level = 0;

        // **** prime q ****
        q.add(root);

        // **** loop while q is not empty ****
        while (!q.isEmpty()) {
 
            // **** remove head node ****
            TreeNode tempNode = q.poll();
            System.out.print(tempNode.val + " ");

            // **** enqueue left child ****
            if (tempNode.left != null)
                t.add(tempNode.left);
 
            // **** enqueue right child ****
            if (tempNode.right != null)
                t.add(tempNode.right);

            // **** ****
            if (q.isEmpty()) {

                // ???? ????
                System.out.println();

                // **** ****
                level++;

                // **** swap queues ****
                q = t;
                t = new LinkedList<TreeNode>();
            }
        }

        // **** ****
        return level;
    }
 





    /**
     * Test scaffolding.
     * 
     * !!! NOT PART OF SOLUTION !!!
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered stream ****
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        // **** read and split input line ****
        String[] strArr = input.readLine().split(",");

        // **** close buffered reader ****
        input.close();

        // **** create and populate integer array (may contain null) ****
        Integer[] arr = new Integer[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals("null"))
                arr[i] = null;
            else
                arr[i] = Integer.parseInt(strArr[i]);
        }

        // ???? ????
        System.out.println("main <<< arr: " + Arrays.toString(arr));

        // **** create and populate binary tree ****
        TreeNode root = populateTree(arr);

        // ???? ????
        System.out.println("main <<< bfs:");
        System.out.print(bfsTraversal(root));

        // **** compute and display number of visible node ****
        System.out.println("main <<< visibleNodes: " + visibleNodes(root));

        // **** find and display the depth of the BT ****
        System.out.println("main <<<        depth: " + maxDepth(root));

        // **** find and display level of BT ****
        System.out.println("main <<< bfs (no null nodes): ");
        System.out.println("main <<<        level: " + levelOrderTraversal(root));
    }
}