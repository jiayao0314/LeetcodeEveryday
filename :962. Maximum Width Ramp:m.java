/*
962. Maximum Width Ramp/m

Given an array A of integers, a ramp is a tuple (i, j) for which i < j and A[i] <= A[j].  
The width of such a ramp is j - i.

Find the maximum width of a ramp in A.  If one doesn't exist, return 0.
*/

class Solution {
    public int maxWidthRamp(int[] A) {
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < A.length; i++) {
            if(stack.isEmpty() || A[stack.peek()] >= A[i]) 
                stack.push(i);
        }
        int res = 0;
        for(int i = A.length - 1; i > res; i--) {
            while(!stack.isEmpty() && A[stack.peek()] <= A[i]) {
                res = Math.max(res, i - stack.pop());
            }
        }
        return res;
    }
}

// O(n), the trick is second for loop begins from the back, kind of greedy
// or O(nlogn), use binary search in the first for loop, when A[i] > A[stack.peek()]