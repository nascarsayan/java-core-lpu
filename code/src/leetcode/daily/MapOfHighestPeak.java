package leetcode.daily;

// https://leetcode.com/problems/map-of-highest-peak/description/

import java.util.ArrayDeque;
import java.util.Deque;

public class MapOfHighestPeak {
    static class Coord {
        int ir, ic;
        Coord(int ir, int ic) {
            this.ir = ir;
            this.ic = ic;
        }

        boolean isValid(int nr, int nc) {
            if (ir < 0 || ir >= nr) return false;
            if (ic < 0 || ic >= nc) return false;
            return true;
        }

        Coord next(Coord diff) {
            return new Coord(ir+diff.ir, ic+diff.ic);
        }
    }
    public int[][] highestPeak(int[][] isWater) {
        var nr = isWater.length;
        var nc = isWater[0].length;
        var res = new int[nr][nc];
        Deque<Coord> frontier = new ArrayDeque<>();
        Coord[] cardinals = {
                new Coord(-1, 0),
                new Coord(0, 1),
                new Coord(1, 0),
                new Coord(0, -1),
        };
        for (int ir=0; ir < nr; ir++) {
            for (int ic=0; ic < nc; ic++) {
                if (isWater[ir][ic] == 1) frontier.addLast(new Coord(ir, ic));
            }
        }
        while (!frontier.isEmpty()) {
            var c = frontier.pollFirst();
            for (var dir: cardinals) {
                var c2 = c.next(dir);
                if (!c2.isValid(nr, nc)) continue;
                if (isWater[c2.ir][c2.ic] == 1) continue;
                if (res[c2.ir][c2.ic] != 0) continue;
                res[c2.ir][c2.ic] = res[c.ir][c.ic] + 1;
                frontier.addLast(c2);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] arr = {
                {0,0,1},
                {1,0,0},
                {0,0,0}
        };
        var res = (new MapOfHighestPeak()).highestPeak(arr);
    }
}
