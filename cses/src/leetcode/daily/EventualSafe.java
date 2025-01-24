package leetcode.daily;

// https://leetcode.com/problems/find-eventual-safe-states/?envType=daily-question&envId=2025-01-24

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EventualSafe {
    private static class Solution {
        enum NodeState {
            // Node is not visited yet.
            Unvisited,
            // Node is unsafe until it's marked safe after complete exploration of sub-tree.
            Unsafe,
            // All nodes visitable from the node was safe, hence node is safe.
            Safe
        }
        int[][] graph;
        int n;
        NodeState[] state;

        private boolean isSafeNode(int u) {
            // Cycle detected, part of the same recursion tree.
            if (state[u] == NodeState.Unsafe) return false;
            // Already confirmed safe by some other recursive call.
            if (state[u] == NodeState.Safe) return true;
            // Mark Node as unsafe until we confirm all the paths from this node
            // lead to safe nodes.
            state[u] = NodeState.Unsafe;
            for (var v: this.graph[u]) {
                if (!isSafeNode(v)) return false;
            }
            state[u] = NodeState.Safe;
            return true;
        }

        public List<Integer> eventualSafeNodes(int[][] graph) {
            this.graph = graph;
            this.n = graph.length;
            this.state = new NodeState[n];
            Arrays.fill(this.state, NodeState.Unvisited);
            var safeNodes = new ArrayList<Integer>();
            for (int u = 0; u < n; u++) {
                if (isSafeNode(u)) safeNodes.add(u);
            }
            return safeNodes;
        }

        public static void main(String[] args) {
            int[][] graph = {{1,2},{2,3},{5},{0},{5},{},{}};
            var res = (new Solution()).eventualSafeNodes(graph);
            System.out.println(res);
        }
    }
}
