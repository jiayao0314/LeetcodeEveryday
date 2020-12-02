/*
907. Sum of Subarray Minimums/m
Given an array of integers A, find the sum of min(B), 
where B ranges over every (contiguous) subarray of A.
*/

class Solution {
    public int sumSubarrayMins(int[] A) {
        int n = A.length, mod = (int)1e9 + 7;
        int[] left = new int[n], right = new int[n];
        Stack<int[]> stack1 = new Stack<>(), stack2 = new Stack<>();
        for(int i = 0; i < n; i++) {
            int cnt = 1;
            while(!stack1.isEmpty() && stack1.peek()[0] > A[i]) {
                cnt += stack1.pop()[1];
            }
            stack1.push(new int[]{A[i], cnt});
            left[i] = cnt;
        }
        for(int j = n-1; j >= 0; j--) {
            int cnt = 1;
            while(!stack2.isEmpty() && stack2.peek()[0] >= A[j]) {
                cnt += stack2.pop()[1];
            }
            stack2.push(new int[]{A[j], cnt});
            right[j] = cnt;
        }
        int res = 0;
        for(int i = 0; i < n; i++) {
            res = (res + left[i] * right[i] * A[i]) % mod;
        }
        return res;
    }
}