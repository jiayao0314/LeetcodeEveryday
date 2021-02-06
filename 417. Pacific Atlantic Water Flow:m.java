// 417. Pacific Atlantic Water Flow/m

// water flow from the ocean to the land

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        Queue<int[]> pqueue = new LinkedList<>();
        Queue<int[]> aqueue = new LinkedList<>();
        int n = matrix.length, m = matrix[0].length;
        boolean[][] pacific = new boolean[n][m];
        boolean[][] atlantic = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            pqueue.offer(new int[]{i, 0});
            aqueue.offer(new int[]{i, m-1});
            pacific[i][0] = true;
            atlantic[i][m-1] = true;
        }
        for(int i = 0; i < m; i++) {
            pqueue.offer(new int[]{0, i});
            aqueue.offer(new int[]{n-1, i});
            pacific[0][i] = true;
            atlantic[n-1][i] = true;
        }
        bfs(matrix, pqueue, pacific);
        bfs(matrix, aqueue, atlantic);
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(pacific[i][j] && atlantic[i][j]) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(i);
                    tmp.add(j);
                    res.add(tmp);
                }
            }
        }
        return res;
    }
    
    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            for(int[] dir: dirs) {
                int x = cur[0] + dir[0];
                int y = cur[1] + dir[1];
                if(x < 0 || x >= matrix.length || y < 0 ||  y >= matrix[0].length || visited[x][y] || matrix[x][y] < matrix[cur[0]][cur[1]]) continue;
                queue.offer(new int[]{x, y});
                visited[x][y] = true;
            }
        }
    }
}