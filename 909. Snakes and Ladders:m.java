// 909. Snakes and Ladders/m

class Solution {
    public int snakesAndLadders(int[][] board) {
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(1);
        visited.add(1);
        int m = board.length, n = board[0].length;
        int max = m * n;
        int step = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            for(int i = 0; i < size; i++) {
                int cur = q.poll();
                if(cur == max) return step;
                for(int next = cur + 1; next <= cur + 6 && next <= max; next++) {
                    int[] coordinate = getCoordinate(next, m, n);
                    int newP = board[coordinate[0]][coordinate[1]] == -1 ? next : board[coordinate[0]][coordinate[1]];
                    if(!visited.contains(newP)) {
                        q.offer(newP);
                        visited.add(newP);
                    }
                }
            }
            step++;
        }
        return -1;
    }
    
    private int[] getCoordinate(int cur, int r, int c) {
        int x = r - 1 - (cur - 1) / c;
        int tmp = (cur - 1) % c;
        int y = ((cur - 1) / c % 2 == 0) ? tmp : c - 1 - tmp;
        return new int[]{x, y};
    }
}