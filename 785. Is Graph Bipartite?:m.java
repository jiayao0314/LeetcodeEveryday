// 785. Is Graph Bipartite?/m

// bfs
public boolean isBipartite(int[][] graph) {
    int len = graph.length;
    int[] colors = new int[len];
    for(int i = 0; i < len; i++) {
        if(colors[i] != 0) continue;
        colors[i] = 1;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(i);
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int next: graph[cur]) {
                
                if(colors[next] == 0) {
                    colors[next] = -colors[cur];
                    queue.add(next);
                } else if(colors[next] != -colors[cur]) {
                    return false;
                }
            }
        }            
    }
    return true;
}