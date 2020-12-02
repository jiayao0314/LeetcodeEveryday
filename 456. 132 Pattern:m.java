/*
456. 132 Pattern/m
Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k] such that i < j < k and nums[i] < nums[k] < nums[j].
*/

// 感觉单调栈有点类似记忆化的贪心
class Solution {
    public boolean find132pattern(int[] nums) {
        if(nums.length < 3) return false;
        Deque<Integer> stack = new ArrayDeque<>();
        int s3 = Integer.MIN_VALUE;
        for(int i = nums.length - 1; i >= 0; i--) {
            if(nums[i] < s3) return true;
            // stack 里存的是目前为止，3 和 2 最大的组合，组合越大，找到满足的 1 就越容易
            while(!stack.isEmpty() && nums[i] > stack.peek())
               s3 = stack.pop();
            stack.push(nums[i]);
        }
        return false;
    }
}