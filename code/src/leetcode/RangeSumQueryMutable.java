package leetcode;

import collections.SegmentTree;

public class RangeSumQueryMutable {
    private static class NumArray {

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

        public NumArray(int[] nums) {
            this.root = buildTree(nums, 0, nums.length - 1);
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
            node.value = node.left.value;
            // The right node might not exist (can be null). Handle null.
            if (node.right != null) {
                node.value += node.right.value;
            }

            return node;
        }


        public void update(int index, int val) {
            pointUpdateTree(root, index, val);
        }

        // Recursive function to update the segment tree
        private void pointUpdateTree(SegmentTreeNode node, int index, int newValue) {
            if (node == null) {
                return;
            }

            if (node.start == node.end) {
                // Leaf node: update the value
                node.value = newValue;
                return;
            }

            int mid = node.start + (node.end - node.start) / 2;

            // The index falls within either [start, mid] or [mid+1, end].
            if (index <= mid) {
                // the index of the affected node falls within [start, mid]
                // Update in the left subtree
                pointUpdateTree(node.left, index, newValue);
            } else {
                // the index of the affected node falls within [mid+1, end]
                // Update in the right subtree
                pointUpdateTree(node.right, index, newValue);
            }

            // Update the current node with the new sum of its children
            node.value = node.left.value + node.right.value;
        }

        public int sumRange(int left, int right) {
            return queryTree(root, left, right);
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
    }
}
