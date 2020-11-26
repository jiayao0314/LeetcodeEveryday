/*
739. Daily Temperatures/m
Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
*/

class Solution {
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stk = new Stack<>();
        if(T.length == 1) return new int[]{0};
        int[] res = new int[T.length];
        for(int i = 0; i < T.length; i++) {
            while(!stk.isEmpty() && T[i] > T[stk.peek()]) {
                int idx = stk.pop();
                res[idx] = i - idx;
            }
            stk.push(i);
        }
        return res;
    }
}