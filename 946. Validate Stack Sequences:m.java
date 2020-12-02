/*
946. Validate Stack Sequences/m
Given two sequences pushed and popped with distinct values, return true if 
and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.

Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
Output: true
*/

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for(int p: pushed) {
            stack.push(p);
            while(i < popped.length && !stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
            
        }

        // return stack.isEmpty() && i == popped.length; 不用 i == popped.length; i 只会小于 len
        return stack.isEmpty()
    }
}