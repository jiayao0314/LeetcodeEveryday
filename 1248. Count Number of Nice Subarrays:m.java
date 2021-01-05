// 1248. Count Number of Nice Subarrays/m


 public int numberOfSubarrays(int[] nums, int k) {
    LinkedList<Integer> deque = new LinkedList<>();
    deque.add(-1);
    int ans = 0;
    for(int i = 0; i < nums.length; i++) {
        if(nums[i] % 2 == 1) deque.add(i);
        if(deque.size() > k + 1) deque.pop();
        if(deque.size() == k + 1) ans += deque.get(1) - deque.get(0);
    }
    return ans;
}