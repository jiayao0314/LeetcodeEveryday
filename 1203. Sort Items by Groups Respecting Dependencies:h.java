// 1203. Sort Items by Groups Respecting Dependencies/h

// dfs, 不是很懂
public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
            
    List<Integer> res = new ArrayList<>();
    List<Integer>[] graph = new ArrayList[n + m];
    int[] indegree = new int[n + m];
    
    for (int i = 0; i < n + m; i++) {
        graph[i] = new ArrayList<>();
    }
    
    for (int i = 0; i < group.length; i++) {
        if (group[i] == -1) {
            continue;
        }
        graph[n + group[i]].add(i);
        indegree[i]++;
    }
    
    for (int i = 0; i < beforeItems.size(); i++) {
        for (int item : beforeItems.get(i)) {
            int repBeforeItem = group[item] == -1 ? item : n + group[item];
            int repCurrentItem = group[i] == -1 ? i : n + group[i];
            
            if (repBeforeItem == repCurrentItem) {
                graph[item].add(i);
                indegree[i]++;
            }
            else {
                graph[repBeforeItem].add(repCurrentItem);
                indegree[repCurrentItem]++;
            }
        }
    }
    
    for (int i = 0; i < n + m; i++) {
        if (indegree[i] == 0) {
            dfs(graph, indegree, i, n, res);
        }
    }
    
    return (res.size() == n) ? res.stream().mapToInt(i -> i).toArray() : new int[]{};
}

private void dfs(List<Integer>[] graph, int[] indegree, int cur, int n, List<Integer> res) {
    if (cur < n) {
        res.add(cur);
    }
    indegree[cur]--;
    
    for (int child : graph[cur]) {
        if (--indegree[child] == 0) {
            dfs(graph, indegree, child, n, res);
        }
    }
}

// ---------------------------------
public  static int findex;
public static int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems){
    //keep all non grouped items into separate groups,
    //so that we find the cycle among all items that belong to individual groups
    for(int i = 0; i < group.length; i++){
        if(group[i] == -1) group[i] = m++;
    }

    ArrayList<Integer>[] groupMap = new ArrayList[m];
    ArrayList<Integer>[] groupItemMap = new ArrayList[m];
    for(int i = 0; i < m; i++){
        groupMap[i] = new ArrayList<>();
        groupItemMap[i] = new ArrayList<>();
    }

    ArrayList<Integer>[] items = new ArrayList[n];
    //prepare group graph, group to item relation from given items
    for(int i = 0; i < n; i++){
        items[i] = new ArrayList<>();
        for(int j : beforeItems.get(i)){
            //if dependent group is not the same group we are walking through
            if(group[j] != group[i]) groupMap[group[i]].add(group[j]);
            items[i].add(j);
        }
        groupItemMap[group[i]].add(i);
    }

    int[] sortedGroups = new int[m];
    //if groups cannot be topsorted, stop
    if(!topsortGroups(groupMap, sortedGroups)) return new int[]{};

    int[] answer = new int[n];
    //if items cannot be topsorted, stop
    if(!topsortItems(groupItemMap, items, answer, sortedGroups)) return new int[]{};
    return answer;
}

private static boolean topsortGroups(ArrayList<Integer>[] groupMap, int[] sortedGroups){
    int[] visited = new int[sortedGroups.length];
    findex = 0;
    for(int i = 0; i < sortedGroups.length; i++){
        if(!dfs(groupMap, visited, sortedGroups, i)) return false;
    }
    return true;
}

private static boolean topsortItems(ArrayList<Integer>[] groupItemMap, ArrayList<Integer>[] items, int[] answer, int[] sortedGroups){
    int[] visited = new int[items.length];
    findex = 0;
    for(int i = 0; i < sortedGroups.length; i++){
        //fetch items belong to group i
        for(int item : groupItemMap[sortedGroups[i]]){
            if(!dfs(items, visited, answer, item)) return false;
        }
    }
    return true;
}

private static boolean dfs(ArrayList<Integer>[] graph, int[] visited, int[] buffer, int node){
    if(visited[node] == 1) return true;
    if(visited[node] == -1) return false; //cycle
    visited[node] = -1;
    for(int i : graph[node]){
        if(!dfs(graph, visited, buffer, i)) return false;
    }
    buffer[findex++] = node;
    visited[node] = 1;
    return true;
}
