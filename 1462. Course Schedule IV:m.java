// 1462. Course Schedule IV/m
public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
    List<Boolean> res = new ArrayList<>();
    Map<Integer, List<Integer>> graph = new HashMap<>();
    Boolean[][] pre = new Boolean[n][n];
    for(int i = 0; i < n; i++) {
        graph.put(i, new ArrayList<>());
    }
    for(int[] p: prerequisites) {
        graph.get(p[0]).add(p[1]);
        pre[p[0]][p[1]] = true;
    }
    for(int[] q: queries) {
        boolean tmp = dfs(q[0], q[1], graph, pre);
        res.add(tmp);
    }
    return res;
}

private boolean dfs(int src, int end, Map<Integer, List<Integer>> graph, Boolean[][] pre) {
    if(pre[src][end] != null) return pre[src][end];
    for(int next: graph.get(src)) {
        boolean isSub;
        if(pre[next][end] == null) {
            isSub = dfs(next, end, graph, pre);
            pre[next][end] = isSub;
        } else{
            isSub = pre[next][end];
        }
        if(isSub) {
            pre[src][end] = true;
            return true;
        }
    }
    pre[src][end] = false;
    return false;
}