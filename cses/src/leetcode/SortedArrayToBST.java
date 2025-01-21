package leetcode;

// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/

import java.util.Arrays;

public class SortedArrayToBST {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode getTree(int[] nums, int st, int fl) {
        if (st > fl) return null;
        var mid = (st + fl) / 2;
        var left = getTree(nums, st, mid-1);
        var right = getTree(nums, mid+1, fl);
        return new TreeNode(nums[mid], left, right);
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        Arrays.sort(nums);
        return getTree(nums, 0, nums.length-1);
    }

    public static void main(String[] args) {
        int[] nums = {-10,-3,0,5,9};
        var res = (new SortedArrayToBST()).sortedArrayToBST(nums);
        System.out.println(res);
    }
}
