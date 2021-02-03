// 1284. Minimum Number of Flips to Convert Binary Matrix to Zero Matrix/h

// m <= 3, n <= 3, convert to bit storing the state of the mat: 1) starting state, 2) fliped state

class Solution {
    public int minFlips(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int start = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                start |= mat[i][j] << (i * n + j);
            }
        }
        if(start == 0) return 0;
        Deque<Integer> q = new LinkedList<>();
        q.offer(start);
        Set<Integer> visited = new HashSet<>();
        visited.add(start);
        int step = 0;
        int[] dirs = {0, 0, 1, 0, -1, 0};
        while(!q.isEmpty()) {
            for(int size = q.size(); size > 0; size--) {
                int cur = q.poll();
                if(cur == 0) return step;
                for(int i = 0; i < m; i++) {
                    for(int j = 0; j < n; j++) {
                        int next = cur;
                        for(int k = 0; k < 5; k++) {
                            int r = i + dirs[k];
                            int c = j + dirs[k + 1];
                            if(r >= 0 && r < m && c >= 0 && c < n)
                                next ^= 1 << (r * n + c);
                        }
                        if(!visited.contains(next)) {
                            q.offer(next);
                            visited.add(next);
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }
}