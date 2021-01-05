// 239. Sliding Window Maximum/h

public int[] maxSlidingWindow(int[] nums, int k) {
    if(nums == null || k <= 0) return new int[0];
    int n = nums.length;
    int[] ans = new int[n - k + 1];
    Deque<Integer> q = new ArrayDeque<>();
    int j = 0;
    for(int i = 0; i < n; i++) {
        while(!q.isEmpty() && q.peek() < i - k + 1) {
            q.poll();
        }
        while(!q.isEmpty() && nums[i] > nums[q.peekLast()]) {
            q.pollLast();
        }
        q.offer(i);
        if(i >= k - 1) ans[j++] = nums[q.peek()];
    }
    return ans;
}

// O(n)
public int[] maxSlidingWindow(int[] nums, int k) {
    if (nums == null || nums.length == 0 || k == 0) {
        return new int[0];
    }
    if (k == 1) {
        return nums;
    }
    
    int n = nums.length;
    int[] res = new int[n - k + 1];
    int[] leftMax = new int[n];
    int[] rightMax = new int[n];
    
    for (int i = 0; i < n; i++) {
        if (i % k == 0) {
            leftMax[i] = nums[i];
        } else {
            leftMax[i] = Math.max(leftMax[i - 1], nums[i]);
        }
    }
    
    for (int i = n - 1; i >= 0; i--) {
        if ((i + 1) % k == 0 || i == n - 1) {
            rightMax[i] = nums[i];
        } else {
            rightMax[i] = Math.max(rightMax[i + 1], nums[i]);
        }
    }
    
    for (int i = 0; i < n - k + 1; i++) {
        res[i] = Math.max(rightMax[i], leftMax[i + k - 1]);
    }
    
    return res;
}