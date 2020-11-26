/*
583. Delete Operation for Two Strings/m
Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.
*/

class Solution {
    public int minDistance(String word1, String word2) {
        int len1 = word1.length(), len2 = word2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for(int i = 1; i <= len1; i++) dp[i][0] = dp[i - 1][0] + 1;
        for(int i = 1; i <= len2; i++) dp[0][i] = dp[0][i - 1] + 1;
        for(int i = 1; i <= len1; i++) {
            for(int j = 1; j <= len2; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + 1;
                }
            }
        }
        return dp[len1][len2];


        int[] dp = new int[word2.length() + 1];
        for (int i = 0; i < word2.length(); i++) dp[i + 1] = dp[i] + 1;
        for (int i = 0; i < word1.length(); i++) {
            int prevRowCol = dp[0];
            dp[0] = dp[0] + 1;
            for (int j = 0; j < word2.length(); j++) {
                int prev = dp[j + 1];
                dp[j + 1] = word1.charAt(i) == word2.charAt(j) ? prevRowCol : Math.min(dp[j + 1], dp[j]) + 1;
                prevRowCol = prev;
            }      
        }
        return dp[word2.length()];
    }
}