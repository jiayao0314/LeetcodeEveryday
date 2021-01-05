// 207. Course Schedule/m

public boolean canFinish(int numCourses, int[][] prerequisites) {
    Map<Integer, List<Integer>> graph = new HashMap<>();
    int[] incomingEdges = new int[numCourses];
    for(int i = 0; i < numCourses; i++)
        graph.put(i, new ArrayList<>());
    for(int[] p: prerequisites) {
        int start = p[1];
        int end = p[0];
        graph.get(start).add(end);
        incomingEdges[end]++;
    }
    
    Queue<Integer> q = new ArrayDeque<>();
    for(int i = 0; i < numCourses; i++) {
        if(incomingEdges[i] == 0)
            q.offer(i);
    }
    
    int num = 0;
    while(!q.isEmpty()) {
        num++;
        int cur = q.poll();
        for(int nei:graph.get(cur)) {
            incomingEdges[nei]--;
            if(incomingEdges[nei] == 0) {
                q.offer(nei);
            }
        }
    }
    return num == numCourses;
}