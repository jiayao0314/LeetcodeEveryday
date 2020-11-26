/*
503. Next Greater Element II/m
Given a circular array (the next element of the last element is the first element of the array), 
print the Next Greater Number for every element. 
The Next Greater Number of a number x is the first greater number to its traversing-order next in the array, 
which means you could search circularly to find its next greater number. 
If it doesn't exist, output -1 for this number.
*/

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stk = new Stack<>();
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        for(int i = 0; i < nums.length*2; i++) {
            int idx = i % nums.length;
            while(!stk.isEmpty() && nums[idx] > nums[stk.peek()]) {
                res[stk.pop()] = nums[idx];
            }
            stk.push(idx);
        }
        return res;
    }
}