"""
https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/?envType=daily-question&envId=2025-02-23
889. Construct Binary Tree from Preorder and Postorder Traversal

[image](@/slides/images/constructFromPreAndPost.svg)

The idea is, if the next node of the preorder traversal is in the left in the postorder traversal, then the node is a child of the current node. Else, the node is a child of some ancesor of the current node.
It is always safe to go left-first while constructing the tree.
"""

# Definition for a binary tree node.
class TreeNode:

    def __init__(
        self,
        val: int = 0,
        left: "TreeNode | None" = None,
        right: "TreeNode | None" = None,
    ):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def constructFromPrePost(self, preorder: list[int], postorder: list[int]) -> TreeNode | None:
        post2idx = { v: i for (i, v) in enumerate(postorder) }
        idx = 0
        def recurse():
            nonlocal idx
            node = TreeNode(preorder[idx])
            idx += 1
            if idx == len(preorder):
                return node
            # next idx in left means recurse left
            if post2idx[preorder[idx]] < post2idx[node.val]:
                node.left = recurse()
            if idx == len(preorder):
                return node
            if post2idx[preorder[idx]] < post2idx[node.val]:
                node.right = recurse()
            return node
        return recurse()
