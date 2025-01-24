package collections;

import java.util.PriorityQueue;

public class Dijkstra {

    // To define the order between 2 elements of the type, we need to
    // implement the Comparable interface.
    // Just define the compareTo method, and write your logic there.
    static class Dest implements Comparable<Dest> {
        int name;
        int totalCost;

        Dest(int name, int totalCost) {
            this.name = name;
            this.totalCost = totalCost;
        }

        @Override
        public int compareTo(Dest o) {
            return this.totalCost - o.totalCost;
        }
    }

    void dijkstra(int[][] graph, int max_v) {
        PriorityQueue<Dest> minHeap = new PriorityQueue<>();

        var inf = Integer.MAX_VALUE;

        int[] minCost = new int[max_v+1];
        // Set all nodes as unreachable
        // Nodes which are reachable will be set
        // while the algorithm is running.
        for (int i = 1; i <= max_v; i++)
            minCost[i] = inf;

        var start = 1;
        minHeap.add(new Dest(start, 0));

        while (!minHeap.isEmpty()) {
            var dst = minHeap.poll();
            var u = dst.name;
            var currCost = dst.totalCost;

            // If visited, we have some value less than infinity.
            if (minCost[u] != inf) continue;

            // Set the minCost here.
            minCost[u] = currCost;

            for (int v = 1; v <= max_v; v++) {
                if (graph[u][v] == 0) continue;
                // Now check if visited.
                if (minCost[v] != inf) continue;

                // Cost to reach v from start via u.
                var cost = currCost+graph[u][v];
                minHeap.add(new Dest(v, cost));
            }
        }

        for (int u = 1; u <= max_v; u++)
            System.out.printf("Min cost to reach %d = %d\n", u, minCost[u]);
    }

    public static void main(String[] args) {
        int max_v = 5;
        int[][] graph = new int[max_v+1][max_v+1];
        graph[1][2] = 4;
        graph[1][3] = 2;
        graph[3][2] = 2;
        graph[2][4] = 3;
        graph[4][5] = 2;
        graph[2][5] = 3;
        graph[3][5] = 4;

        (new Dijkstra()).dijkstra(graph, max_v);
    }
}
