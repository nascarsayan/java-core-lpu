package cses;

import java.util.*;

public class MovieFestival {
    static class Movie implements Comparable<Movie> {
        int start;
        int end;

        Movie(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Movie other) {
            if (this.end != other.end)
                return this.end - other.end;
            // descending order of start time if end time is equal.
            return other.start - this.start;
        }
    }

    public static int maxMovies(int n, int[][] times) {
        if (times == null || times.length == 0) return 0;

        // Convert to Movie objects and sort
        List<Movie> movies = new ArrayList<>();
        for (int[] time : times) {
            movies.add(new Movie(time[0], time[1]));
        }
        Collections.sort(movies);

        int count = 1;  // We can always watch at least the first movie
        int end = movies.get(0).end;

        // Check each subsequent movie
        for (int i = 1; i < movies.size(); i++) {
            if (movies.get(i).start >= end) {
                count++;
                end = movies.get(i).end;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] times = new int[n][2];

        for (int i = 0; i < n; i++) {
            times[i][0] = scanner.nextInt();
            times[i][1] = scanner.nextInt();
        }

        System.out.println(maxMovies(n, times));
        scanner.close();
    }

    // Test cases
    public static void runTests() {
        // Example from problem statement
        assert maxMovies(3, new int[][]{{3, 5}, {4, 9}, {5, 8}}) == 2;

        // Non-overlapping movies
        assert maxMovies(3, new int[][]{{1, 2}, {3, 4}, {5, 6}}) == 3;

        // All overlapping
        assert maxMovies(3, new int[][]{{1, 10}, {2, 9}, {3, 8}}) == 1;

        // Empty input
        assert maxMovies(0, new int[][]{}) == 0;

        System.out.println("All tests passed!");
    }
}