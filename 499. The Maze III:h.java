// 499. The Maze III/h

// pq call out compareTo() function. only update points after comparing: points[cur.x][cur.y].compareTo(cur) > 0

class Solution {
    class Point implements Comparable<Point> {
        int x, y, l;
        String s;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.l = Integer.MAX_VALUE;
            this.s = "";
        }
        public Point(int x, int y, int l, String s) {
            this.x = x;
            this.y = y;
            this.l = l;
            this.s = s;
        }
        public int compareTo(Point o) {
            return l == o.l ? s.compareTo(o.s) : l - o.l;
        }
    }
    
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        Point[][] points = new Point[m][n];
        for(int i = 0; i < m * n; i++) {
            points[i / n][i % n] = new Point(i / n, i % n);
        }
        int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        String[] strs = new String[]{"u", "r", "d", "l"};
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(ball[0], ball[1], 0, ""));
        while(!pq.isEmpty()) {
            Point cur = pq.poll();
            if(points[cur.x][cur.y].compareTo(cur) <= 0) continue;
            points[cur.x][cur.y] = cur;
            for(int i = 0; i < 4; i++) {
                int curx = cur.x;
                int cury = cur.y;
                int curl = cur.l;
                while(curx >= 0 && curx < m && cury >= 0 && cury < n 
                      && maze[curx][cury] == 0 && (curx != hole[0] || cury != hole[1])) {
                    curx += dirs[i][0];
                    cury += dirs[i][1];
                    curl++;
                }
                if(curx != hole[0] || cury != hole[1]) {
                    curx -= dirs[i][0];
                    cury -= dirs[i][1];
                    curl--;
                }
                pq.offer(new Point(curx, cury, curl, cur.s + strs[i]));
            }
        }
        return points[hole[0]][hole[1]].l == Integer.MAX_VALUE ? "impossible" : points[hole[0]][hole[1]].s;
    }
}