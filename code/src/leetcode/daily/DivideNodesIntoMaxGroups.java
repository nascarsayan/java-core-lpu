package leetcode.daily;

// https://leetcode.com/problems/divide-nodes-into-the-maximum-number-of-groups/?envType=daily-question&envId=2025-01-30

// TODO: Does not work.

import java.util.ArrayDeque;
import java.util.ArrayList;

public class DivideNodesIntoMaxGroups {
    static class Solution {
        ArrayList<Integer>[] graph;
        int n;

        boolean isBipartite() {
            enum Color {
                Red, Blue;

                Color next() {
                    switch(this) {
                        case Red: return Blue;
                        case Blue: return Red;
                    }
                    return null;
                }
            }
            var colors = new Color[n+1];
            for (int i = 1; i <= n; i++) {
                if (colors[i] != null) continue;
                colors[i] = Color.Red;
                var q = new ArrayDeque<Integer>();
                q.addLast(i);
                while (!q.isEmpty()) {
                    var u = q.pollFirst();
                    for (var v: graph[u]) {
                        if (colors[v] != null) {
                            if (colors[v] == colors[u]) return false;
                            continue;
                        }
                        colors[v] = colors[u].next();
                        q.addLast(v);
                    }
                }
            }
            return true;
        }

        static class NodeDist {
            int node;
            int dist;

            NodeDist(int node, int dist) {
                this.node = node;
                this.dist = dist;
            }
        }

        NodeDist bfs(int start, Integer[] dist) {
            var frontier = new ArrayDeque<Integer>();
            if (dist == null) {
                dist = new Integer[n+1];
            }
            frontier.addLast(start);
            var lastNode = start;
            dist[start] = 0;
            while (!frontier.isEmpty()) {
                var u = frontier.pollFirst();
                for (var v: graph[u]) {
                    if (dist[v] != null) continue;
                    dist[v] = dist[u] + 1;
                    lastNode = v;
                    frontier.addLast(v);
                }
            }
            return new NodeDist(lastNode, dist[lastNode]);
        }

        public int magnificentSets(int n, int[][] edges) {
            this.n = n;
            graph = new ArrayList[n+1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (var e: edges) {
                int u = e[0], v = e[1];
                graph[u].addLast(v);
                graph[v].addLast(u);
            }
            if (!isBipartite()) return -1;
            int res = 0;
            var dist = new Integer[n+1];
            for (int u = 1; u <= n; u++) {
                if (dist[u] != null) continue;
                var nd = bfs(u, null);
                var nd2 = bfs(nd.node, dist);
                res += nd2.dist + 1;
            }
            return res;
        }

        public static void main(String[] args) {
            int[][] edges = {{1,2},{1,4},{1,5},{2,6},{2,3},{4,6}};
            var res = (new Solution()).magnificentSets(6, edges);
            System.out.println(res);
        }
    }
}
