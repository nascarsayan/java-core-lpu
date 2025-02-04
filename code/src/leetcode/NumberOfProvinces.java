package leetcode;

import java.util.HashSet;

public class NumberOfProvinces {
    private static class Solution {

        int[] parent, rank;

        int find(int index) {
            if (parent[index] != index) {
                // This is not the leader.
                // Search for the leader and set it.
                parent[index] = find(parent[index]);
            }
            return parent[index];
        }

        void union(int u, int v) {
            var leaderU = find(u);
            var leaderV = find(v);
            if (leaderU == leaderV) return;
            // union
            if (rank[leaderU] < rank[leaderV]) {
                // leaderV is deeper. Set the subtree of leaderU under leaderV.
                parent[leaderV] = leaderU;
                return;
            }
            if (rank[leaderV] < rank[leaderU]) {
                // leaderU is deeper. Set the subtree of leaderV under leaderU.
                parent[leaderU] = leaderV;
                return;
            }
            // Both are equally deep. You can set it either way, and increase the rank of the leader of both.
            parent[leaderU] = leaderV;
            rank[leaderV]++;
        }

        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }

            for (int u = 0; u < n; u++) {
                for (int v = u+1; v < n; v++) {
                    if (isConnected[u][v] != 1) continue;
                    // merge the sets corresponding to u and v.
                    union(u, v);
                }
            }

            // find the leaders and add them to a set.
            var leaders = new HashSet<Integer>();
            for (int index = 0; index < n; index++) {
                leaders.add(find(index));
            }
            return leaders.size();
        }
    }
}
