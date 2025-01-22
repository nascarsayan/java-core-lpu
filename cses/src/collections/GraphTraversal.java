package collections;

import java.util.ArrayDeque;
import java.util.Queue;

public class GraphTraversal {

    public void BFS(Graph.AdjacencyMatrix gr, int max_v) {
        ArrayDeque<Integer> frontier = new ArrayDeque<>();
        boolean[] visited = new boolean[max_v+1];

        var start = 1;
        frontier.addLast(start);
        visited[start] = true;
        System.out.printf("Have visited 1\n");

        while (!frontier.isEmpty()) {
            var u = frontier.pollFirst();
            // visit all locations possible from u.
            for (int v = 1; v <= max_v; v++) {
                // no edge present, can't visit v from u.
                if (gr.graph[u][v] == 0) continue;
                // However, some location has already been visited. If visited, skip.
                if (visited[v]) continue;
                frontier.addLast(v);
                visited[v] = true;
                System.out.printf("Have visited %d\n", v);
            }
        }
    }

    public static void main(String[] args) {
        // for 9 nodes, we create 10x10 amtrix with row 0 and col 0 empty, for the convenience of programmer.
        var gr = new Graph.AdjacencyMatrix(10);
        gr.addEdgeDirected(1, 2, 1);
        gr.addEdgeDirected(1, 3, 1);
        gr.addEdgeDirected(1, 4, 1);
        gr.addEdgeDirected(2, 5, 1);
        gr.addEdgeDirected(2, 6, 1);
        gr.addEdgeDirected(3, 7, 1);
        gr.addEdgeDirected(3, 8, 1);
        gr.addEdgeDirected(3, 9, 1);
        gr.addEdgeDirected(2, 1, 1);
        gr.addEdgeDirected(5, 6, 1);
        gr.addEdgeDirected(7, 2, 1);
        gr.addEdgeDirected(9, 4, 1);

        (new GraphTraversal()).BFS(gr, 10);
    }
}
