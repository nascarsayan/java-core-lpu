package leetcode.daily;

import java.util.Arrays;

public class MaxEmployeesToBeInvited {
    static class Solution {
        enum NodeState {
            // Node is not visited yet.
            Unvisited,
            // Node is being visited as part of the current recursion.
            Visiting,
            // Node has already been visited as part of a previous recursion.
            Visited
        }
        int[] favorite;
        NodeState[] visit;
        int[] depth;

        int dfs(int u, int currDepth) {
            if (visit[u] == NodeState.Visiting) return currDepth - depth[u];
            if (visit[u] == NodeState.Visited) return 0;
            visit[u] = NodeState.Visiting;
            depth[u] = currDepth;
            var cycleLen = dfs(favorite[u], currDepth+1);
            visit[u] = NodeState.Visited;
            return cycleLen;
        }
        public int maximumInvitations(int[] favorite) {
            this.favorite = favorite;
            this.visit = new NodeState[favorite.length];
            this.depth = new int[favorite.length];
            Arrays.fill(visit, NodeState.Unvisited);
            int res = 0;
            for (int u = 0; u < favorite.length; u++) {
                res = Math.max(dfs(u, 0), res);
            }
            // TODO: Also count the number of acyclic chains.
            return res;
        }

        public static void main(String[] args) {
            int[] fav = {2,2,1,2};
            var res = (new Solution()).maximumInvitations(fav);
            System.out.println(res);
        }
    }
}
