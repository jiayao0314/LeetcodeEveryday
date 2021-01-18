// 787. Cheapest Flights Within K Stops/m

public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
    Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
    for(int[] p: flights) {
        prices.putIfAbsent(p[0], new HashMap<>());
        prices.get(p[0]).put(p[1], p[2]);
    }
    // costs, src, stops
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
    pq.add(new int[]{0, src, 0});
    while(!pq.isEmpty()) {
        int[] cur = pq.poll();
        int cost = cur[0];
        int city = cur[1];
        int stop = cur[2];
        if(city == dst) return cost;
        if(stop <= K) {
            Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
            for(int c: adj.keySet()) {
                pq.offer(new int[]{cost + adj.get(c), c, stop + 1});
            }
        }
    }
    return -1;
}