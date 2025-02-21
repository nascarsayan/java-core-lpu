"""
https://leetcode.com/problems/find-elements-in-a-contaminated-binary-tree/
1261. Find Elements in a Contaminated Binary Tree
"""

# Definition for a binary tree node.
class TreeNode:

    def __init__(
        self, val: int=0, left: "TreeNode | None" = None, right: "TreeNode | None" = None
    ):
        self.val = val
        self.left = left
        self.right = right


class FindElements:

    def __init__(self, root: TreeNode | None):
        def recurse(node: TreeNode | None, val: int):
            if node is None:
                return
            node.val = val
            recurse(node.left, val * 2 + 1)
            recurse(node.right, val * 2 + 2)

        recurse(root, 0)
        self.root = root

    def find(self, target: int) -> bool:
        bi = f"{(target+1):b}"

        def recurse(node: TreeNode | None, idx: int) -> bool:
            if node is None:
                return False
            if idx == len(bi):
                return True
            if bi[idx] == "0":
                return recurse(node.left, idx + 1)
            else:
                return recurse(node.right, idx + 1)

        return recurse(self.root, 1)


# Your FindElements object will be instantiated and called as such:
# obj = FindElements(root)
# param_1 = obj.find(target)
