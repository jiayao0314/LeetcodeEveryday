// 1039. Minimum Score Triangulation of Polygon/m

class Solution {
    public int minScoreTriangulation(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        for (int d = 2; d < n; ++d) {
            for (int i = 0; i + d < n; ++i) {
                int j = i + d;
                // 初始化一个取不到的值
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; ++k)
                	// 闭区间。重叠的边可以重复用，所以是 [i,k], [k,j]
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j] + A[i] * A[j] * A[k]);
            }
        }
        return dp[0][n - 1];
    }
}