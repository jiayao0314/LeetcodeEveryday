// 743. Network Delay Time/m

public int networkDelayTime(int[][] times, int N, int K) {
    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
    for(int[] t: times) {
        map.putIfAbsent(t[0], new HashMap<>());
        map.get(t[0]).put(t[1], t[2]);
    }
    // pq, dist increasing
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
    boolean[] visited = new boolean[N + 1];
    pq.add(new int[]{K, 0});
    int res = 0;
    while(!pq.isEmpty()) {
        int[] cur = pq.poll();
        int curNode = cur[0];
        int curDist = cur[1];
        if(visited[curNode]) continue;
        visited[curNode] = true;
        N--;
        res = curDist;
        if(map.containsKey(curNode)) {
            for(int next: map.get(curNode).keySet()) {
                pq.add(new int[]{next, res + map.get(curNode).get(next)});
            }
        }
    }
    if(N != 0) return -1;
    return res;
}