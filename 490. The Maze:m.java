// 490. The Maze/m

// bfs
public boolean hasPath(int[][] maze, int[] start, int[] destination) {
    int rows = maze.length, cols = maze[0].length;
    boolean[][] visited = new boolean[rows][cols];
    Queue<int[]> q = new ArrayDeque<>();
    q.add(start);
    visited[start[0]][start[1]] = true;
    
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    while(!q.isEmpty()) {
        int size = q.size();
        for(int i = 0; i < size; i++) {
            int[] cur = q.poll();
            if(cur[0] == destination[0] && cur[1] == destination[1]) return true;
            for(int[] d: dirs) {
                int x = cur[0];
                int y = cur[1];
                while(isValid(maze, x + d[0], y + d[1])) {
                    x += d[0];
                    y += d[1];
                }
                if(visited[x][y] == false) {
                    visited[x][y] = true;
                    q.offer(new int[]{x, y});
                } 
            }
        }
        
    }
    return false;
}

private boolean isValid(int[][] maze, int i, int j) {
    if(i < 0 || i >= maze.length || j < 0 || j >= maze[0].length || maze[i][j] == 1) return false;
    return true;
}


// dfs
int[][] dir = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
int m;
int n;
int idx;

boolean flag = false;
public boolean hasPath(int[][] A, int[] start, int[] destination) {
	boolean[][] v = new boolean[A.length][A[0].length];
    
     m = A.length; 
     n = A[0].length;
	 return dfs(A, start, destination, v);	
}

boolean dfs(int[][] A, int[] s, int[] e, boolean[][] v){
    if(v[s[0]][s[1]]) return false;
    if(s[0] == e[0] && s[1] == e[1]) return true;
    v[s[0]][s[1]] = true;

    for(int[] d : dir){
        int row = s[0];
        int col = s[1];
        while(valid(A, row + d[0], col + d[1], m, n)){
            row = row + d[0];
            col = col + d[1];
        }        
        if(dfs(A, new int[]{row, col}, e, v)) return true; 
    }
    return false;
}

boolean valid(int[][] A, int row, int col, int m, int n){
   if(row >= 0 && row < m && col >= 0 && col < n && A[row][col] != 1) return true;
   return false;
}