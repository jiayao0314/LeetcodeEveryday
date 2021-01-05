// 862. Shortest Subarray with Sum at Least K/h

public int shortestSubarray(int[] A, int K) {
    int n = A.length;
    int[] B = new int[n + 1];
    for(int i = 0; i < n; i++) B[i + 1] = B[i] + A[i];
    int len = n + 1;
    Deque<Integer> q = new ArrayDeque<>();
    for(int i = 0; i < n + 1; i++) {
        while(!q.isEmpty() && B[i] <= B[q.getLast()]) {
            q.pollLast();
        }
        while(!q.isEmpty() && B[i] - B[q.getFirst()] >= K) {
            len = Math.min(len, i - q.pollFirst());
        }
        q.addLast(i);
    }
    if(len == n + 1) return -1;
    return len;
}