// 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit/m

public int longestSubarray(int[] A, int limit) {
    Deque<Integer> maxd = new ArrayDeque<>();
    Deque<Integer> mind = new ArrayDeque<>();
    int ans = 0, start = 0;
    for(int end = 0; end < A.length; end++) {
        while(!maxd.isEmpty() && A[end] > maxd.peekLast()) maxd.pollLast();
        while(!mind.isEmpty() && A[end] < mind.peekLast()) mind.pollLast();
        maxd.add(A[end]);
        mind.add(A[end]);
        while(!maxd.isEmpty() && !mind.isEmpty() && maxd.peekFirst() - mind.peekFirst() > limit) {
            if(maxd.peekFirst() == A[start]) maxd.pollFirst();
            if(mind.peekFirst() == A[start]) mind.pollFirst();
            start++;
        }
        ans = Math.max(ans, end - start + 1);
    }
    return ans;
}