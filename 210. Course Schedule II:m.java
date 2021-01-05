// 210. Course Schedule II/m

public int[] findOrder(int numCourses, int[][] prerequisites) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Queue<Integer> q = new ArrayDeque<>();
    int[] incomingEdges = new int[numCourses];
    int[] ans = new int[numCourses];
    int cnt = 0;
    for(int i = 0; i < numCourses; i++) graph.put(i, new ArrayList<>());
    for(int[] p: prerequisites) {
        int start = p[1];
        int end = p[0];
        graph.get(start).add(end);
        incomingEdges[end]++;
    }
    for(int i = 0; i < numCourses; i++) {
        if(incomingEdges[i] == 0) q.offer(i);
    }
    while(!q.isEmpty()) {
        int cur = q.poll();
        ans[cnt] = cur;
        for(int nei: graph.get(cur)) {
            incomingEdges[nei]--;
            if(incomingEdges[nei] == 0) {
                q.offer(nei);
            }
        }
        cnt++;
    }
    if(cnt == numCourses) return ans;
    return new int[0];
}