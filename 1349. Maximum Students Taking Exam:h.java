/*
1349. Maximum Students Taking Exam/h

20.11.12, discuss 有篇很好的总结
- Bitmasking = bit + mask (use 0/1 to represent the state of something. In most cases, 1 stands for the valid state while 0 stands for the invalid state)
- handling problems: "what is the i-th bit in the state" or "what is the number of valid bits in a state". 
- coding tricks: 
(x >> i) & 1: get i-th bit in state x
(x & y) == x: check if x is a subset of y
(x & (x >> 1)) == 0: check if there are no adjancent valid states in x

Time: O(m * 4^n), m is the number of rows, n is the number of columns of matrix (m, n <= 8).
Space: O(m * 2^n)

@zerotrac2 @hiepit
*/

class Solution {
    public int maxStudents(char[][] seats) {
        int m = seats.length, n = seats[0].length;
        int[] validRows = new int[m];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                validRows[i] = (validRows[i] << 1) + (seats[i][j] == '.' ? 1: 0);
            }
        }
        // 2^n states for n columns in binary format
        int stateSize = 1 << n;
        int[][] dp = new int[m][stateSize];
        for(int i = 0; i < m; i++) Arrays.fill(dp[i], -1);
        int ans = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < stateSize; j++) {
                // (j & valid) == j: check if j is a subset of valid
		        // !(j & (j >> 1)): check if there is no adjancent students in the row
                if((j & validRows[i]) == j && (j & (j >> 1)) == 0) {
                    if(i == 0) {
                        dp[i][j] = Integer.bitCount(j);
                    } else {
                        for(int k = 0; k < stateSize; k++) {
                            // !(j & (k >> 1)): no students in the upper left positions
						    // !(j & (k << 1)): no students in the upper right positions
						    // dp[i-1][k] != -1: the previous state is valid
                            if(dp[i - 1][k] != -1 && (j & (k >> 1)) == 0 && (j & (k << 1)) == 0) {
                                dp[i][j] = Math.max(dp[i][j], dp[i - 1][k] + Integer.bitCount(j));
                            }
                        }
                    }
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans;
    }
}