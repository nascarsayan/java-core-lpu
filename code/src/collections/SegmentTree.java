package collections;

public class SegmentTree {
    private static class SegmentTreeNode {
        int start, end; // Range represented by this node
        int value;      // Value stored in this node (e.g., sum of the range)
        SegmentTreeNode left, right; // Left and right child nodes

        // Constructor for a node
        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.value = 0; // Initialize the value to 0
            this.left = null;
            this.right = null;
        }
    }
    private SegmentTreeNode root;

    // Constructor to build the segment tree
    public SegmentTree(int[] arr) {
        this.root = buildTree(arr, 0, arr.length - 1);
    }

    // Recursive function to build the segment tree
    private SegmentTreeNode buildTree(int[] arr, int start, int end) {
        if (start > end) {
            return null; // Base case: invalid range
        }

        // Create a new node for the current range
        SegmentTreeNode node = new SegmentTreeNode(start, end);

        if (start == end) {
            // Leaf node: stores the value of a single element
            node.value = arr[start];
            return node;
        }

        int mid = start + (end - start) / 2;

        // Recursively build the left and right subtrees
        node.left = buildTree(arr, start, mid);
        node.right = buildTree(arr, mid + 1, end);

        // Internal node: stores the sum of its children
        node.value = node.left.value + node.right.value;

        return node;
    }

    // Function to query the sum of elements in a range [queryLeft, queryRight]
    public int query(int queryLeft, int queryRight) {
        return queryTree(root, queryLeft, queryRight);
    }

    // Recursive function to query the segment tree
    private int queryTree(SegmentTreeNode node, int queryLeft, int queryRight) {
        if (node == null || queryRight < node.start || queryLeft > node.end) {
            // Fully outside the query range
            return 0;
        }

        if (queryLeft <= node.start && node.end <= queryRight) {
            // Fully inside the query range
            return node.value;
        }

        // Partially overlapping: split and recurse
        return queryTree(node.left, queryLeft, queryRight) +
                queryTree(node.right, queryLeft, queryRight);
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree st = new SegmentTree(arr);

        // Query the sum of elements in range [1, 3]
        System.out.println("Sum in range [1, 3]: " + st.query(1, 3)); // Output: 15

    }
}
