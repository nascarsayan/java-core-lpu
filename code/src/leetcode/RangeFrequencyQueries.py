from collections import Counter
from dataclasses import dataclass
from typing import List, Optional


@dataclass
class SegmentTreeNode:
    start: int
    end: int
    freq: Counter[int]
    left: Optional['SegmentTreeNode'] = None
    right: Optional['SegmentTreeNode'] = None


class RangeFreqQuery:
    def __init__(self, arr: List[int]):
        self.root = self._build_tree(arr, 0, len(arr) - 1)
    
    def _build_tree(self, arr: List[int], start: int, end: int) -> Optional[SegmentTreeNode]:
        if start > end:
            return None
            
        node = SegmentTreeNode(start=start, end=end, freq=Counter())
        
        if start == end:
            node.freq[arr[start]] = 1
            return node
            
        mid = (start + end) // 2
        node.left = self._build_tree(arr, start, mid)
        node.right = self._build_tree(arr, mid + 1, end)
        
        # Merge frequencies from children
        node.freq = Counter()
        if node.left:
            node.freq.update(node.left.freq)
        if node.right:
            node.freq.update(node.right.freq)
            
        return node
    
    def query(self, left: int, right: int, value: int) -> int:
        def _query(node: Optional[SegmentTreeNode], query_left: int, query_right: int) -> int:
            if not node or query_right < node.start or query_left > node.end:
                return 0
                
            if query_left <= node.start and node.end <= query_right:
                return node.freq[value]
                
            return (_query(node.left, query_left, query_right) + 
                   _query(node.right, query_left, query_right))
        
        return _query(self.root, left, right)
