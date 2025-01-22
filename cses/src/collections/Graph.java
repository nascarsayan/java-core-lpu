package collections;

import java.util.*;

public class Graph {
    static class AdjacencyMatrix {
        // count_v -> Count of vertices
        int[][] graph;
        AdjacencyMatrix(int count_v) {
            graph = new int[count_v][count_v];
        }

        void addEdgeDirectedNoWeight(int u, int v) {
            graph[u][v] = 1;
        }

        void addEdgeUndirected(int u, int v, int weight) {
            graph[u][v] = weight;
            graph[v][u] = weight;
        }

        void addEdgeDirected(int u, int v, int weight) {
            graph[u][v] = weight;
        }

        public static void main(String[] args) {
            AdjacencyMatrix gr = new AdjacencyMatrix(5);
            gr.addEdgeDirected(1, 2, 10);
            gr.addEdgeDirected(1, 3, 15);
            gr.addEdgeDirected(3, 2, 8);
            gr.addEdgeDirected(2, 4, 5);
            System.out.println("works!");
        }
    }

    static class AdjacencyList {
        static class Edge {
            Node dst;
            int cost;

            Edge(Node dst, int cost) {
                this.dst = dst;
                this.cost = cost;
            }
        }
        static class Node {
            int name;

            Node(int name) {
                this.name = name;
            }

            @Override
            public boolean equals(Object obj) {
                if (!(obj instanceof Node)) return false;
                var other = (Node)obj;
                if (other.name == this.name) return true;
                return false;
            }

        }
        Set<Node> nodes = new HashSet<>();
        Map<Node, ArrayList<Edge>> edges = new HashMap<>();

        Node addNode(int u) {
            var node_u = new Node(u);
            // TODO: We need to make this O(1). Currently O(n).
            for (var n: nodes) {
                if (n.equals(node_u)) return n;
            }
            nodes.add(node_u);
            return node_u;
        }

        void addEdgeDirectedNoWeight(int u, int v) {
            var node_u = addNode(u);
            var node_v = addNode(v);
            var edges_from_u = edges.getOrDefault(node_u, new ArrayList<Edge>());
            edges_from_u.addLast(new Edge(node_v, 1));
            edges.put(node_u, edges_from_u);
        }

        void addEdgeUndirected(int u, int v, int weight) {
            var node_u = addNode(u);
            var node_v = addNode(v);
            var edges_from_u = edges.getOrDefault(node_u, new ArrayList<Edge>());
            var edges_from_v = edges.getOrDefault(node_v, new ArrayList<Edge>());
            edges_from_u.addLast(new Edge(node_v, weight));
            edges_from_v.addLast(new Edge(node_u, weight));
            edges.put(node_u, edges_from_u);
            edges.put(node_v, edges_from_v);
        }

        void addEdgeDirected(int u, int v, int weight) {
            var node_u = addNode(u);
            var node_v = addNode(v);
            var edges_from_u = edges.getOrDefault(node_u, new ArrayList<Edge>());
            edges_from_u.addLast(new Edge(node_v, weight));
            edges.put(node_u, edges_from_u);
        }

        public static void main(String[] args) {
            AdjacencyList gr = new AdjacencyList();
            gr.addEdgeDirected(1, 2, 10);
            gr.addEdgeDirected(1, 3, 15);
            gr.addEdgeDirected(3, 2, 8);
            gr.addEdgeDirected(2, 4, 5);
            System.out.println("works!");
        }
    }
}
