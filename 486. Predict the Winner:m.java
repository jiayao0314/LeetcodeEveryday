// 486. Predict the Winner/m

// Input: [1, 5, 2]
// Output: False
// Explanation: Initially, player 1 can choose between 1 and 2. 
// If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
// So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
// Hence, player 1 will never be the winner and you need to return False.

class Solution {
    public boolean PredictTheWinner(int[] nums) {
        // 递归拆解大问题，自底向上求解子问题。
        // 然后改用 for 循环取代递归，按顺序求解子问题，去填充二维数组
        // 即，dp是自上而下（递归）地思考，但自下而上地解决
        // "dp is not about filling in tables, but about smart recursion"
        int n = nums.length;
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }
        for(int i = n - 2; i >= 0; i--) {
            for(int j = i + 1; j < n; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1]);
            }
        }
        return dp[0][n - 1] >= 0;
    }
}