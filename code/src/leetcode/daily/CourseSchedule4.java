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
            if (this.allPrereq[u] != null) return;
            this.allPrereq[u] = new HashSet<>();
            for (var v: graph[u]) {
                dfs(v);
                this.allPrereq[u].addAll(this.allPrereq[v]);
                this.allPrereq[u].add(v);
            }
        }

        public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
            this.numCourses = numCourses;
            this.graph = new HashSet[numCourses];
            for (int i = 0; i < numCourses; i++)
                this.graph[i] = new HashSet<>();
            for (var p: prerequisites) {
                this.graph[p[1]].add(p[0]);
            }
            this.allPrereq = new HashSet[numCourses];
            for (int u = 0; u < numCourses; u++) {
                dfs(u);
            }
            ArrayList<Boolean> res = new ArrayList<>();
            for (var q: queries) {
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
