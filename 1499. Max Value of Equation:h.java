// 1499. Max Value of Equation/h

// peek() head, offer() tail, poll() head
class Solution {
    public int constrainedSubsetSum(int[] nums, int k) {
        int res = nums[0], j = 0;
        Deque<Integer> d = new ArrayDeque<>();
        for(int i = 0; i < nums.length; i++) {
            // d.peek() head, always the largest
            nums[i] += d.isEmpty() ? 0 : d.peek();
            res = Math.max(res, nums[i]);
            while(!d.isEmpty() && nums[i] > d.peekLast()) {
                d.pollLast();
            }
            if(nums[i] > 0) d.offer(nums[i]);
            if(!d.isEmpty() && i >= k && d.peek() == nums[i - k]) {
                d.poll();
            }
        }
        return res;
    }
}