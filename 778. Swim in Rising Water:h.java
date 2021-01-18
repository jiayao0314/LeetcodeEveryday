// 778. Swim in Rising Water/h
// dfs, bfs, union find, binary search, priority queue

// dfs
public int swimInWater(int[][] grid) {
    int time = 0;
    Set<Integer> seen = new HashSet<>();
    int n = grid.length;
    while(!seen.contains(n * n - 1)) {
        seen.clear();
        dfs(grid, 0, 0, n, time, seen);
        time++;
    }
    return time - 1;
}

int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
private void dfs(int[][] grid, int i, int j, int n, int time, Set<Integer> seen) {
    if(i < 0 || i >= n || j < 0 || j >= n || grid[i][j] > time || seen.contains(i * n + j)) return;
    seen.add(i * n + j);
    for(int[] d: dirs) {
        dfs(grid, i + d[0], j + d[1], n, time, seen);
    }
}

// union find
class UF {
    int[] id;
    public UF(int n) {
        id = new int[n];
        for(int i = 0; i < n; i++) {
            id[i] = i;
        }
    }
    
    public int root(int i) {
        while(id[i] != i) {
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    
    public boolean isConnected(int i, int j) {
        return root(i) == root(j);
    }
    
    public void union(int i, int j) {
        if(root(i) == root(j)) return;
        id[root(i)] = root(j);
    }
}

public int swimInWater(int[][] grid) {
    int N = grid.length;
    UF uf = new UF(N*N);
    int time = 0;
    while(!uf.isConnected(0, N*N-1)) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] > time) continue;
                if (i < N-1 && grid[i+1][j]<=time) uf.union(i*N+j, i*N+j+N);
                if (j < N-1 && grid[i][j+1]<=time) uf.union(i*N+j, i*N+j+1);
            }
        }
        time++;
    }
    return time - 1;
}