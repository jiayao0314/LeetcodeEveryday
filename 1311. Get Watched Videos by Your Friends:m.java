// 1311. Get Watched Videos by Your Friends/m

// 直接用 while loop 最后的 queue 不需要创建新的 neiList 来存，lambda sort 里可以用其他变量来对比：Collections.sort(res, (a, b)->(map.get(a) == map.get(b) ? a.compareTo(b) : map.get(a) - map.get(b)));

class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        // List<Integer> neiList = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[friends.length];
        q.offer(id);
        visited[id] = true;
        // not while(level != 0), but add break inside
        while(!q.isEmpty()) {
            if(level == 0) break;
            for(int size = q.size(); size > 0; size--) {
                int cur = q.poll();
                for(int nei: friends[cur]) {
                    if(!visited[nei]) {
                        q.offer(nei);
                        visited[nei] = true;
                        // if(level == 0) neiList.add(nei);
                    }
                }
            }
            level--;
        }
        // if(neiList.size() == 0) return new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        while(!q.isEmpty()) {
            for(String v: watchedVideos.get(q.poll())) {
                map.put(v, map.getOrDefault(v, 0) + 1);
            }
        }
        List<String> res = new ArrayList<>();
        for(String k: map.keySet()) res.add(k);
        Collections.sort(res, (a, b)->(map.get(a) == map.get(b) ? a.compareTo(b) : map.get(a) - map.get(b)));
        return res;
    }
}