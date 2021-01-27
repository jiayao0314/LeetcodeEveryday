// 407. Trapping Rain Water II/h

class Cell {
    int row;
    int col;
    int height;
    
    public Cell(int r, int c, int h) {
        row = r;
        col = c;
        height = h;
    } 
}
public int trapRainWater(int[][] heightMap) {
    int m = heightMap.length, n = heightMap[0].length;
    boolean[][] visited = new boolean[m][n];
    PriorityQueue<Cell> pq = new PriorityQueue<>((a, b) -> a.height - b.height);
    for(int i = 0; i < m; i++) {
        visited[i][0] = true;
        visited[i][n - 1] = true;
        pq.offer(new Cell(i, 0, heightMap[i][0]));
        pq.offer(new Cell(i, n - 1, heightMap[i][n - 1]));
    }
    for(int j = 0; j < n; j++) {
        visited[0][j] = true;
        visited[m - 1][j] = true;
        pq.offer(new Cell(0, j, heightMap[0][j]));
        pq.offer(new Cell(m - 1, j, heightMap[m - 1][j]));
    }
    
    int res = 0;
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    while(!pq.isEmpty()) {
        Cell cur = pq.poll();
        for(int[] d: dirs) {
            int x = cur.row + d[0];
            int y = cur.col + d[1];
            if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]) {
                visited[x][y] = true;
                res += Math.max(0, cur.height - heightMap[x][y]);
                pq.offer(new Cell(x, y, Math.max(cur.height, heightMap[x][y])));
            }
        }
    }
    return res;
}