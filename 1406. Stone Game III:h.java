/*
1406. Stone Game III/h

Alice and Bob take turns, with Alice starting first. On each player's turn, 
that player can take 1, 2 or 3 stones from the first remaining stones in the row.
*/

class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length, dp[] = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;
            for (int k = 0, take = 0; k < 3 && i + k < n; k++) {
                take += stoneValue[i + k];
                dp[i] = Math.max(dp[i], take - dp[i + k + 1]);
            }
        }
        if (dp[0] > 0) return "Alice";
        if (dp[0] < 0) return "Bob";
        return "Tie";
    }
}