// 312. Burst Balloons/h

class Solution {
    public int maxCoins(int[] iNums) {
        int[] nums = new int[iNums.length + 2];
        int n = 1;
        for (int x : iNums) if (x > 0) nums[n++] = x;
        // [8] -> 1*8*1，所以头尾要存 1
        nums[0] = nums[n++] = 1;


        int[][] dp = new int[n][n];
        for (int d = 2; d < n; d++)
            for (int i = 0; i + d < n; i++) {
                int j = i + d;
                for (int k = i + 1; k < j; k++)
                	// 这里求 max，dp 初始化为 0，所以不用赋值
                    dp[i][j] = Math.max(dp[i][j], 
                    nums[i] * nums[k] * nums[j] + dp[i][k] + dp[k][j]);
            }

        return dp[0][n - 1];
    }
}