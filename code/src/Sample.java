import java.util.*;

public class Sample {
    enum Cell {
        EMPTY,      // represents '.' (was 0)
        VISITED,    // for BFS marking (was 1)
        WALL        // represents '#' (was 2)
    }

    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nr = sc.nextInt();
        int nc = sc.nextInt();
        sc.nextLine(); // consume newline

        // Initialize graph with padding
        Cell[][] graph = new Cell[nr + 2][nc + 2];
        for (Cell[] row : graph) {
            Arrays.fill(row, Cell.WALL);
        }

        // Read the grid
        for (int ir = 1; ir <= nr; ir++) {
            String line = sc.nextLine();
            for (int ic = 1; ic <= nc; ic++) {
                if (line.charAt(ic - 1) == '.') {
                    graph[ir][ic] = Cell.EMPTY;
                }
            }
        }

        // Direction vectors for adjacent cells
        Pair[] dirs = {
                new Pair(-1, 0),  // up
                new Pair(0, 1),   // right
                new Pair(1, 0),   // down
                new Pair(0, -1)   // left
        };

        int islands = 0;
        // BFS to find connected components
        for (int ir = 1; ir <= nr; ir++) {
            for (int ic = 1; ic <= nc; ic++) {
                if (graph[ir][ic] != Cell.EMPTY) continue;

                Deque<Pair> frontier = new ArrayDeque<>();
                frontier.add(new Pair(ir, ic));
                graph[ir][ic] = Cell.VISITED;

                while (!frontier.isEmpty()) {
                    Pair curr = frontier.pollFirst();
                    int jr = curr.first;
                    int jc = curr.second;
                    graph[jr][jc] = Cell.VISITED;

                    for (Pair dir : dirs) {
                        int kr = jr + dir.first;
                        int kc = jc + dir.second;

                        // if (!validCoord(kr, kc, nr, nc)) continue;
                        if (graph[kr][kc] != Cell.EMPTY) continue;

                        frontier.add(new Pair(kr, kc));
                        graph[kr][kc] = Cell.VISITED;
                    }
                }
                islands++;
            }
        }

        System.out.println(islands);
        sc.close();
    }

    // private static boolean validCoord(int ir, int ic, int nr, int nc) {
    //     return 1 <= ir && ir <= nr && 1 <= ic && ic <= nc;
    // }
}