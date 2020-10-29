// 375. Guess Number Higher or Lower II/m

class Solution {
    // dfs + 剪枝 top down
    public int getMoneyAmount(int n) {
        // 先选所有 pattern 的最坏情况，再从这些结果里面取个最好的 pattern
        // dp + 剪枝，能 cover 右边大数的肯定能 cover 左边
        int[][] dp = new int[n + 1][n + 1];
        return dfs(dp, 1, n);
    }
    
    public int dfs(int[][] dp, int left, int right) {
        if(left >= right) return 0;
        if(left + 1 == right) 
            return left;
        if(dp[left][right] != 0)
            return dp[left][right];
        int res = Integer.MAX_VALUE;
        int mid = left + (right - left) / 2;
        for(int i = right - 1; i >= mid; i -= 2) {
            int tmp = i + Math.max(dfs(dp, left, i - 1), dfs(dp, i + 1, right));
            res = Math.min(res, tmp);
        }
        dp[left][right] = res;
        return res;
    }
    
    // dp bottom up
     public int getMoneyAmount(int n) {
        int[][] table = new int[n+1][n+1];
        for(int j=2; j<=n; j++){
            // 从 “dp bottom up 是解决小问题再到大问题”的角度理解，看别人写的 py 代码可能更好理解
            for(int i=j-1; i>0; i--){
                int globalMin = Integer.MAX_VALUE;
                for(int k=i+1; k<j; k++){
                    int localMax = k + Math.max(table[i][k-1], table[k+1][j]);
                    globalMin = Math.min(globalMin, localMax);
                }
                table[i][j] = i+1==j?i:globalMin;
            }
        }
        return table[1][n];
     }
}