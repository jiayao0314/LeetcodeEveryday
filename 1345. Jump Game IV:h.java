// 1345. Jump Game IV/h

public int minJumps(int[] arr) {
    Map<Integer, List<Integer>> map = new HashMap<>();
    for(int i = 0; i < arr.length; i++) {
        map.computeIfAbsent(arr[i], x -> new ArrayList<>()).add(i);
    }
    int step = 0;
    Queue<Integer> q = new ArrayDeque<>();
    boolean[] visited = new boolean[arr.length];
    q.offer(0); visited[0] = true;
    while(!q.isEmpty()) {
        int size = q.size();
        for(int i = 0; i < size; i++) {
            int cur = q.poll();
            if(cur == arr.length - 1) return step;
            List<Integer> tmp = map.get(arr[cur]);
            tmp.add(cur - 1);
            tmp.add(cur + 1);
            for(int v: tmp) {
                if(v >= 0 && v < arr.length && !visited[v]) {
                    if(!visited[v]) q.offer(v);
                    visited[v] = true;
                }
            }
            tmp.clear();
        }
        step++;
    }
    return -1;
}