### Union-Find (Disjoint Set Union - DSU) in Java

The **Union-Find** data structure, also known as the **Disjoint Set Union (DSU)**, is a powerful tool used to manage collections of disjoint sets efficiently. It supports two primary operations:

1. **Find**: Determine which set a particular element belongs to.
2. **Union**: Merge two sets into one.

#### Key Features:
- **Path Compression**: Optimizes the `find` operation by flattening the structure of the tree, making future queries faster.
- **Union by Rank/Size**: Ensures that smaller trees are attached to larger ones, keeping the tree balanced and improving efficiency.

#### Implementation in Java:
Here’s a basic implementation of the Union-Find data structure in Java:

```java
class UnionFind {
    private int[] parent;
    private int[] rank;

    // Constructor to initialize the Union-Find structure
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i; // Each element is its own parent initially
            rank[i] = 1;   // Initially, each set has a rank of 1
        }
    }

    // Find the root of the set containing element x with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    // Union two sets containing elements x and y using union by rank
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return false; // Already in the same set
        }

        // Union by rank
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }

    // Check if two elements are connected (i.e., in the same set)
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }
}
```

---

### LeetCode Problems to Practice Union-Find

Here are some popular LeetCode problems where the Union-Find algorithm can be applied:

#### 1. **Number of Connected Components in an Undirected Graph**
   - **Problem**: Given `n` nodes labeled from `0` to `n - 1` and a list of undirected edges, determine the number of connected components in the graph.
   - **LeetCode Link**: [323. Number of Connected Components in an Undirected Graph](https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/)
   - **Approach**: Use Union-Find to group connected nodes into the same set and count the number of unique roots.

#### 2. **Redundant Connection**
   - **Problem**: In a tree with `n` nodes labeled from `1` to `n`, there is exactly one additional edge added to make it no longer a tree. Find the redundant edge.
   - **LeetCode Link**: [684. Redundant Connection](https://leetcode.com/problems/redundant-connection/)
   - **Approach**: Use Union-Find to detect cycles. If adding an edge creates a cycle, that edge is redundant.

#### 3. **Accounts Merge**
   - **Problem**: Given a list of accounts where each account consists of a name and a list of email addresses, merge accounts that share at least one email address.
   - **LeetCode Link**: [721. Accounts Merge](https://leetcode.com/problems/accounts-merge/)
   - **Approach**: Use Union-Find to group emails belonging to the same account and then merge them.

#### 4. **Most Stones Removed with Same Row or Column**
   - **Problem**: On a 2D plane, stones are placed at integer coordinates. You can remove a stone if it shares the same row or column with another stone. What is the maximum number of stones you can remove?
   - **LeetCode Link**: [947. Most Stones Removed with Same Row or Column](https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/)
   - **Approach**: Use Union-Find to group stones that are connected either by row or column and calculate the number of removable stones.

#### 5. **Friend Circles**
   - **Problem**: There are `N` students in a class. Some of them are friends, while others are not. Friendship is transitive. Determine the total number of friend circles among all students.
   - **LeetCode Link**: [547. Friend Circles](https://leetcode.com/problems/friend-circles/)
   - **Approach**: Use Union-Find to group students into friend circles based on their friendship connections.

#### 6. **Satisfiability of Equality Equations**
   - **Problem**: Given an array of strings `equations` representing relationships between variables, determine if it's possible to assign integers to variable names so as to satisfy all the equations.
   - **LeetCode Link**: [990. Satisfiability of Equality Equations](https://leetcode.com/problems/satisfiability-of-equality-equations/)
   - **Approach**: Use Union-Find to group variables that must be equal and check for contradictions.

#### 7. **Regions Cut By Slashes**
   - **Problem**: In a grid of slashes (`/`) and backslashes (`\`), determine how many distinct regions are formed.
   - **LeetCode Link**: [959. Regions Cut By Slashes](https://leetcode.com/problems/regions-cut-by-slashes/)
   - **Approach**: Use Union-Find to connect cells in the grid based on the slashes and count the number of connected regions.

#### 8. **Smallest String With Swaps**
   - **Problem**: Given a string `s` and a list of pairs of indices, you can swap characters at any pair of indices. Return the lexicographically smallest string you can obtain.
   - **LeetCode Link**: [1202. Smallest String With Swaps](https://leetcode.com/problems/smallest-string-with-swaps/)
   - **Approach**: Use Union-Find to group indices that can be swapped and sort characters within each group.

---

### Tips for Solving Union-Find Problems:
1. **Understand the Problem**: Identify whether the problem involves grouping, connectivity, or merging of elements.
2. **Optimize with Path Compression and Union by Rank**: These optimizations reduce the time complexity of the `find` and `union` operations to nearly constant time (`O(α(n))`, where `α` is the inverse Ackermann function).
3. **Handle Edge Cases**: Be mindful of scenarios like disconnected graphs, empty inputs, or invalid operations.
4. **Practice**: Solve a variety of problems to get comfortable with different applications of Union-Find.

By practicing these problems, you'll gain a deeper understanding of the Union-Find data structure and its applications in solving real-world problems efficiently.
