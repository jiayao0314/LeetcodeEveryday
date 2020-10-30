// 877. Stone Game/m

class Solution {
    public boolean stoneGame(int[] piles) {
        // dp[i][j]：区间 piles[i..j] 内先手可以获得的相对分数
        int n = piles.length;
        int[][] dp = new int[n][n];
        for(int i= 0; i < n; i++) {
            dp[i][i] = piles[i];
        }
        /*
       x --> dp[i][j]
         x      ^
            x   |
              x |
                x
        */
        for(int j = 1; j < n; j++) {
            for(int i = j - 1; i >= 0; i--) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] > 0;
    }
}