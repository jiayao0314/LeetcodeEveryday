// 864. Shortest Path to Get All Keys/h

// aiming to collect keys, not unlock the locks. but with key u can pass the lock

class Solution {
    class State{
        int x, y, k;
        public State(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }
    
    public int shortestPathAllKeys(String[] grid) {
        int m = grid.length, n = grid[0].length(), startx = -1, starty = -1, target = -1;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i].charAt(j) == '@') {
                    startx = i;
                    starty = j;
                }
                if(grid[i].charAt(j) >= 'a' && grid[i].charAt(j) <= 'f') {
                    target = Math.max(target, grid[i].charAt(j) - 'a' + 1);
                }
            }
        }
        State start = new State(startx, starty, 0);
        Deque<State> q = new LinkedList<>();
        q.offer(start);
        int step = 0;
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Set<String> visited = new HashSet<>();
        visited.add(0 + "-" + startx + "-" + starty);
        while(!q.isEmpty()) {
            for(int size = q.size(); size > 0; size--) {
                State cur = q.poll();
                if(cur.k == (1 << target) - 1) return step;
                for(int[] d: dirs) {
                    int r = cur.x + d[0];
                    int c = cur.y + d[1];
                    int key = cur.k;
                    if(r >= 0 && r < m && c >= 0 && c < n) {
                        char ch = grid[r].charAt(c);
                        if(ch == '#') continue;
                        if(ch >= 'a' && ch <= 'f') key |= 1 << (ch - 'a');
                        if(ch >= 'A' && ch <= 'F' && ((key >> (ch - 'A')) & 1) == 0) continue;
                        if(!visited.contains(key + "-" + r + "-" + c)) {
                            visited.add(key + "-" + r + "-" + c);
                            q.offer(new State(r, c, key));
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
}