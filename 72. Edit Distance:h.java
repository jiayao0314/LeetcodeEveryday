/*72. Edit Distance/h
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character
*/

// 经典状态压缩
class Solution {
    public int minDistance(String word1, String word2) {
        // int len1 = word1.length(), len2 = word2.length();
        // int[][] dp = new int[len1 + 1][len2 + 1];
        // for(int i = 1; i <= len1; i++) dp[i][0] = len1;
        // for(int i = 1; i <= len2; i++) dp[0][i] = len2;
        // for(int i = 1; i <= len1; i++) {
        //     for(int j = 1; j <= len2; j++) {
        //         if(word1.charAt(i - 1) != word2.charAt(j - 1)) {
        //             dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
        //         } else dp[i][j] = dp[i - 1][j - 1];
        //     }
        // }
        // return dp[len1][len2];
        
        int[] dp = new int[word2.length() + 1];
        for (int i = 1; i < dp.length; i++) dp[i] = dp[i - 1] + 1;
        for (int i = 0; i < word1.length(); i++) {
            int prevRowCol = dp[0];
            dp[0] = dp[0] + 1;
            for (int j = 1; j <= word2.length(); j++) {
                int prev = dp[j];
                int result;
                if (word1.charAt(i) == word2.charAt(j - 1)) result = prevRowCol;
                else {
                    result = Math.min(dp[j], prevRowCol);
                    result = Math.min(result, dp[j - 1]);
                    result += 1;
                }
                dp[j] = result;
                prevRowCol = prev;
            }
        }
        return dp[word2.length()];
    }
}