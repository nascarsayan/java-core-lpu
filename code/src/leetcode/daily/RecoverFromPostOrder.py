"""
https://leetcode.com/problems/recover-a-tree-from-preorder-traversal/?envType=daily-question&envId=2025-02-22
1028. Recover a Tree From Preorder Traversal
"""

# Definition for a binary tree node.
class TreeNode:

    def __init__(
        self, val: int = 0, left: "TreeNode|None" = None, right: "TreeNode|None" = None
    ):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def getNodes(self, tr: str):
        nodes: list[tuple[int, int]] = []
        idx = 0
        dep = 0
        while idx < len(tr):
            if tr[idx] == "-":
                dep += 1
                idx += 1
                continue
            fl = idx + 1
            while fl < len(tr) and tr[fl] != "-":
                fl += 1
            nodes.append((dep, int(tr[idx:fl])))
            idx = fl
            dep = 0
        return nodes

    def recoverFromPreorder(self, tr: str) -> TreeNode | None:
        nodes = self.getNodes(tr)
        idx = 0

        def build(depth: int) -> TreeNode | None:
            nonlocal idx
            if idx == len(nodes):
                return None
            nd, nv = nodes[idx]
            if nd != depth:
                return None
            node = TreeNode(nv)
            idx += 1
            node.left = build(depth + 1)
            node.right = build(depth + 1)
            return node

        return build(0)
