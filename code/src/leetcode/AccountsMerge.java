package leetcode;

import java.util.*;

public class AccountsMerge {
    private static class Solution {
        private static class UnionFind {
            private int[] parent, rank;
            UnionFind(int n) {
                this.parent = new int[n];
                this.rank = new int[n];
                for (int i = 0; i < n; i++) {
                    this.parent[i] = i;
                    this.rank[i] = 1;
                }
            }

            int find(int index) {
                if (parent[index] != index) {
                    parent[index] = find(parent[index]);
                }
                return parent[index];
            }

            void union(int u, int v) {
                var leaderU = find(u);
                var leaderV = find(v);
                if (leaderU == leaderV) return;
                if (rank[leaderU] < rank[leaderV]) {
                    parent[leaderU] = leaderV;
                    return;
                }
                if (rank[leaderV] < rank[leaderU]) {
                    parent[leaderV] = leaderU;
                    return;
                }
                parent[leaderV] = leaderU;
                rank[leaderU]++;
            }
        }
        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            int n = accounts.size();
            HashSet<String>[] emails = new HashSet[n];
            for (int i = 0; i < n; i++) {
                emails[i] = new HashSet<>();
                for (int j = 1; j < accounts.get(i).size(); j++) {
                    emails[i].add(accounts.get(i).get(j));
                }
            }
            var uf = new UnionFind(n);
            for (int u = 0; u < n; u++) {
                for (int v = u+1; v < n; v++) {
                    var intersection = new HashSet<String>(emails[u]);
                    intersection.retainAll(emails[v]);
                    if (intersection.size() == 0) continue;
                    uf.union(u, v);
                }
            }
            // map of [index of accounts] -> [index of res]
            HashSet<String> knownEmails = new HashSet<>();
            HashMap<Integer, Integer> leader2ResIdx = new HashMap<>();
            List<List<String>> res = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                var leader = uf.find(i);
                var resIdx = leader2ResIdx.getOrDefault(leader, -1);
                if (resIdx == -1) {
                    var arr = new ArrayList<String>();
                    res.addLast(arr);
                    resIdx = res.size() - 1;
                    leader2ResIdx.put(leader, resIdx);
                }
                for (int j = 1; j < accounts.get(i).size(); j++) {
                    var email = accounts.get(i).get(j);
                    if (knownEmails.contains(email)) continue;
                    knownEmails.add(email);
                    res.get(resIdx).addLast(email);
                }
            }
            for (var leader: leader2ResIdx.keySet()) {
                var resIdx = leader2ResIdx.get(leader);
                Collections.sort(res.get(resIdx));
                res.get(resIdx).addFirst(accounts.get(leader).getFirst());
            }
            return res;
        }
    }
}
