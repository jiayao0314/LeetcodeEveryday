/*
1140. Stone Game II/m

仔细思考，为什么 dfs + memo 比 dp 快，m 的范围不同？
前者正序递归，每层都会传入一定范围的 m，即上一层递归的 Math.max(i, m)。
后者逆推，m 的范围未知，区间是 [ 1, piles.length ]
两者时间复杂度分别是什么？
*/

class Solution {
    /*
    取左边的连续的石子堆，先从右往左计算“前缀和”
    全部拿走，否则枚举 1<= x <= 2M，计算当前选手能拿的最大数量，取 max，因为是理性人，每轮递归 A 和 L 都会 play optimally
    */
    
    // dfs
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] presum = new int[n + 1];
        for(int i = n - 1; i >= 0; i--) {
            presum[i] = presum[i + 1] + piles[i];
        }
        return dfs(presum, 1, 0, new int[n][n]);
    }
    
    private int dfs(int[] presum, int m, int idx, int[][] memo) {
        if(idx + 2 * m >= presum.length) {
            return presum[idx];
        }
        if(memo[idx][m] > 0) return memo[idx][m];
        int tmp = 0, take = 0, otherTake = 0, left = 0;
        for(int i = 1; i <= 2 * m; i++) {
            take = presum[idx] - presum[idx + i];
            otherTake = dfs(presum, Math.max(i, m), idx + i, memo);
            left = presum[idx + i] - otherTake;
            tmp = Math.max(tmp, take + left);
        }
        memo[idx][m] = tmp;
        return tmp;
    }
    
    
    // dp
    // 从后往前推，M 未知，枚举所有可能。
    public int stoneGameII(int[] piles) {  
        int n = piles.length;
        int[] presum = new int[n + 1];
        for(int i = n - 1; i >= 0; i--) {
            presum[i] = presum[i + 1] + piles[i];
        }
        int[][] dp = new int[n][n + 1];
        for(int i = n - 1; i >= 0; i--) {
            for(int M = 1; M <= n; M++) {
                if(i + 2 * M >= n) {
                    dp[i][M] = presum[i];
                    continue;
                }
                for(int x = 1; x <= 2 * M; x++) {
                    dp[i][M] = Math.max(dp[i][M], presum[i] - dp[i + x][Math.max(x, M)]);
                }
            }
        }
        return dp[0][1];
    }
    
    /*
    两种计算 presum 的方法
    int[] presum = Arrays.copyOf(piles, piles.length);
    for(int i = presum.length -2; i >=0 ; i--) {
        presum[i] += presum[i + 1];
    }
    
    int[] presum = new int[piles.length + 1];
    for(int i = piles.length - 1; i >= 0; i--) {
        presum[i] = presum[i + 1] + piles[i];
    }
    */
}