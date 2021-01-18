// 505. The Maze II/m

public int shortestDistance(int[][] maze, int[] start, int[] destination) {
    int height = maze.length, width = maze[0].length;
    int[][] distance = new int[height][width];
    for(int[] d: distance) {
        Arrays.fill(d, -1);
    }
    distance[start[0]][start[1]] = 0;
    Queue<int[]> q = new ArrayDeque<>();
    q.offer(start);
    
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    while(!q.isEmpty()) {
        int[] cur = q.poll();
        
        for(int[] d: dirs) {
            int space = distance[cur[0]][cur[1]];
            int x = cur[0];
            int y = cur[1];
            while(x + d[0] >= 0 && x + d[0] < height &&
                 y + d[1] >= 0 && y + d[1] < width &&
                 maze[x + d[0]][y + d[1]] != 1) {
                x += d[0];
                y += d[1];
                space++;
            }
            if(distance[x][y] == -1 || distance[x][y] > space) {
                distance[x][y] = space;
                q.offer(new int[]{x, y});
            }
        }

    }
    return distance[destination[0]][destination[1]];
}