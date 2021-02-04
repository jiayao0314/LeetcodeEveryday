// 1368. Minimum Cost to Make at Least One Valid Path in a Grid/h

// greedily explore the grid: for each point in the q do bfs to explore 4 directions, for each point in the same cost level, do the dfs to extend.

class Solution {
    
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        int cost = 0;
        dfs(grid, 0, 0, cost, dp, q);
        
        while(!q.isEmpty()) {
            cost++;
            for(int size = q.size(); size > 0; size--) {
                int[] cur = q.poll();
                for(int[] d: dirs) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];
                    dfs(grid, x, y, cost, dp, q);
                }
            }
        }
        return dp[m - 1][n - 1];
    }
    
    private void dfs(int[][] grid, int r, int c, int cost, int[][] dp, Queue<int[]> q) {
        if(r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && dp[r][c] == Integer.MAX_VALUE) {
            dp[r][c] = cost;
            q.offer(new int[]{r, c});
            int[] next = dirs[grid[r][c] - 1];
            dfs(grid, r + next[0], c + next[1], cost, dp, q);
        } 
    }
}