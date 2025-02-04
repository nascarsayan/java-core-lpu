package leetcode;
import java.util.HashMap;

public class RangeFrequencyQueries {
    private static class RangeFreqQuery {

        private static class SegmentTreeNode {
            int start, end; // Range represented by this node
            HashMap<Integer, Integer> freqAcc;      // Value stored in this node (e.g., sum of the range)
            SegmentTreeNode left, right; // Left and right child nodes

            // Constructor for a node
            public SegmentTreeNode(int start, int end) {
                this.start = start;
                this.end = end;
                this.freqAcc = new HashMap<Integer, Integer>(); // Initialize the value to 0
                this.left = null;
                this.right = null;
            }
        }
        private SegmentTreeNode root;

        // Recursive function to build the segment tree
        private SegmentTreeNode buildTree(int[] arr, int start, int end) {
            if (start > end) {
                return null; // Base case: invalid range
            }

            // Create a new node for the current range
            SegmentTreeNode node = new SegmentTreeNode(start, end);

            if (start == end) {
                // Leaf node: stores the value of a single element
                node.freqAcc = new HashMap<>();
                node.freqAcc.put(arr[start], 1);
                return node;
            }

            int mid = start + (end - start) / 2;

            // Recursively build the left and right subtrees
            node.left = buildTree(arr, start, mid);
            node.right = buildTree(arr, mid + 1, end);

            // Internal node: stores the sum of its children
            node.freqAcc = new HashMap<>(node.left.freqAcc);
            // The right node might not exist (can be null). Handle null.
            if (node.right != null) {
                for (var k: node.right.freqAcc.keySet()) {
                    node.freqAcc.merge(k, node.right.freqAcc.get(k), Integer::sum);
                }
            }

            return node;
        }

        public RangeFreqQuery(int[] arr) {
            this.root = buildTree(arr, 0, arr.length - 1);
        }

        // Recursive function to query the segment tree
        private int queryTree(SegmentTreeNode node, int queryLeft, int queryRight, int value) {
            if (node == null || queryRight < node.start || queryLeft > node.end) {
                // Fully outside the query range
                return 0;
            }

            if (queryLeft <= node.start && node.end <= queryRight) {
                // Fully inside the query range
                return node.freqAcc.getOrDefault(value, 0);
            }

            // Partially overlapping: split and recurse
            return queryTree(node.left, queryLeft, queryRight, value) +
                    queryTree(node.right, queryLeft, queryRight, value);
        }

        public int query(int left, int right, int value) {
            return queryTree(root, left, right, value);
        }
    }
}
