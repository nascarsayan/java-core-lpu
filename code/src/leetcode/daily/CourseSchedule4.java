package leetcode.daily;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CourseSchedule4 {
    static class Solution {

        int numCourses;
        HashSet<Integer>[] graph;
        HashSet<Integer>[] allPrereq;

        void dfs(int u) {
            // if u is visited, then allPrereq[u] will not be null
            if (this.allPrereq[u] != null) return;
            this.allPrereq[u] = new HashSet<>();
            for (var v: graph[u]) {
                // first, compute all prereqs of my current child.
                dfs(v);
                // add all prereqs of my child.
                this.allPrereq[u].addAll(this.allPrereq[v]);
                // add my child.
                this.allPrereq[u].add(v);
            }
        }

        public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
            this.numCourses = numCourses;
            // adjacency list.
            this.graph = new HashSet[numCourses];
            for (int i = 0; i < numCourses; i++)
                this.graph[i] = new HashSet<>();
            // as we are traversing the graph, we go deep to the
            // basic courses first.
            for (var p: prerequisites) {
                // p[0] is the basic course.
                // p[1] is the advanced course.
                // p[1] depends on p[0] being completed first.
                this.graph[p[1]].add(p[0]);
            }
            this.allPrereq = new HashSet[numCourses];
            for (int u = 0; u < numCourses; u++) {
                dfs(u);
            }
            ArrayList<Boolean> res = new ArrayList<>();
            for (var q: queries) {
                // [uj, vj]
                // For the jth query, you should answer whether course uj is a prerequisite of course vj or not.
                res.addLast(this.allPrereq[q[1]].contains(q[0]));
            }
            return res;
        }

        public static void main(String[] args) {
            int[][] x = {{1,2},{1,0},{2,0}};
            int[][] q = {{1,0},{1,2}};
            var y = (new Solution()).checkIfPrerequisite(3, x, q);
            System.out.println(y);
        }
    }
}
