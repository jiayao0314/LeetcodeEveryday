// 1696. Jump Game VI/m

public int maxResult(int[] nums, int k) {
    Deque<Integer> d = new ArrayDeque<>();
    int n = nums.length;
    int[] sum = new int[n + 1];
    for(int i = 0; i < n; i++) {
        int preMax = d.isEmpty()? 0 : sum[d.peekFirst()];
        sum[i] = nums[i] + preMax;
        while(!d.isEmpty() && sum[d.peekLast()] < sum[i]) {
            d.pollLast();
        }
        while(!d.isEmpty() && i - d.peekFirst() >= k) {
            d.pollFirst();
        }
        d.add(i);
    }
    return sum[n - 1];
}