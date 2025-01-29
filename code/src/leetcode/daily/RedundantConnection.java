package leetcode.daily;

public class RedundantConnection {
    static class Solution {
        static class UnionFind {
            private int[] parent; // Parent array for Union-Find
            private int[] rank;   // Rank array for union by rank

            // Constructor to initialize the Union-Find structure
            public UnionFind(int size) {
                parent = new int[size + 1]; // Nodes are labeled from 1 to size
                rank = new int[size + 1];
                for (int i = 1; i <= size; i++) {
                    parent[i] = i; // Each node is its own parent initially
                    rank[i] = 1;   // Rank is initialized to 1
                }
            }

            // Find function with path compression
            public int find(int x) {
                if (parent[x] != x) {
                    parent[x] = find(parent[x]); // Path compression
                }
                return parent[x];
            }

            // Union function with union by rank
            public boolean union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);

                // If both nodes have the same root, they are already connected
                if (rootX == rootY) {
                    return false; // No union performed
                }

                // Union by rank: attach the smaller tree under the root of the larger tree
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++; // Increase rank if both trees have the same depth
                }

                return true; // Union performed successfully
            }
        }
        public int[] findRedundantConnection(int[][] edges) {
            int n = edges.length;
            UnionFind uf = new UnionFind(n); // Initialize Union-Find structure

            // Iterate through each edge
            for (int[] edge : edges) {
                int u = edge[0];
                int v = edge[1];

                // Try to union the two nodes
                if (!uf.union(u, v)) {
                    // If union fails, this edge is redundant
                    return edge;
                }
            }

            return new int[0]; // No redundant edge found (should not happen as per problem constraints)
        }
    }
}
