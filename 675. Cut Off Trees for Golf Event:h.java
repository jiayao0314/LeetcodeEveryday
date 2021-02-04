// 675. Cut Off Trees for Golf Event/h

// cut in order, can walk except 0
// in the bfs reduce the overlapping positions:  If you set the visited to true after a node is polled from the queue, you will get TLE. However, if you set visited to true immediately after it is add into the queue, you will be fine

class Solution {
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public int cutOffTree(List<List<Integer>> forest) {
        
        int m = forest.size(), n = forest.get(0).size();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(forest.get(i).get(j) > 1) {
                    pq.offer(new int[]{i, j, forest.get(i).get(j)});
                }
            }
        }
        
        int step = 0;
        int[] start = new int[2];
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int path = findMinPath(forest, start, cur, m, n);
            if(path == -1) return -1;
            step += path;
            start[0] = cur[0];
            start[1] = cur[1];
        }
        return step;
    }
    
    public int findMinPath(List<List<Integer>> forest, int[] start, int[] end, int m, int n) {
        Deque<int[]> q = new LinkedList<>();
        q.offer(start);
        boolean[][] visited = new boolean[m][n];
        visited[start[0]][start[1]] = true;
        int step = 0;
        while(!q.isEmpty()) {
            for(int size = q.size(); size > 0; size--) {
                int[] cur = q.poll();
                if(cur[0] == end[0] && cur[1] == end[1]) return step;
                for(int[] d: dirs) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];
                    if(x >= 0 && x < m && y >= 0 && y < n && forest.get(x).get(y) != 0 && !visited[x][y]) {
                        visited[x][y] = true;
                        q.offer(new int[]{x, y});
                    }
                }
            }
            step++;
        }
        return -1;
    }
}