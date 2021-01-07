// 444. Sequence Reconstruction/m

// 问题转化：拓扑排序，判断是否是无环的有向图，是则true

class Solution {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for(List<Integer> seq: seqs) {
            for(int i = 0; i < seq.size(); i++) {
                graph.putIfAbsent(seq.get(i), new ArrayList<>());
                indegree.putIfAbsent(seq.get(i), 0);
                if(i > 0) {
                    graph.get(seq.get(i - 1)).add(seq.get(i));
                    indegree.put(seq.get(i), indegree.get(seq.get(i)) + 1);
                }
            }
        }
        
        if(org.length != indegree.size()) return false;
        Queue<Integer> q = new LinkedList<>();
        int idx = 0;
        for(Map.Entry<Integer, Integer> entry: indegree.entrySet()) {
            if(entry.getValue() == 0) {
                q.add(entry.getKey());
            }
        }
        while(!q.isEmpty()) {
            if(q.size() > 1) return false;
            int cur = q.poll();
            if(org[idx++] != cur) return false;
            for(int nei: graph.get(cur)) {
                indegree.put(nei, indegree.get(nei) - 1);
                if(indegree.get(nei) == 0) {
                    q.add(nei);
                }
            }
        }
        return idx == org.length;
    }
}


/* 
two conditions check:
1. Every sequence in seqs should be a subsequence in org. 
2. Every 2 consecutive elements in org should be consecutive elements in some sequence from seqs.
*/
public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
    if(seqs == null || seqs.size() == 0) return false;
    int orglen = org.length;
    int[] idx = new int[orglen+1];
    boolean[] pairs = new boolean[orglen];
    
    for(int i = 0; i < orglen; i++){
        idx[org[i]] = i;
    }
    for(List<Integer> seq: seqs){
        for(int i = 0; i < seq.size(); i++){
            if(seq.get(i) > orglen || seq.get(i) < 0) return false;
            if(i > 0 && idx[seq.get(i-1)] >= idx[seq.get(i)]) return false;
            if(i > 0 && idx[seq.get(i-1)]+1 == idx[seq.get(i)]){
                pairs[idx[seq.get(i-1)]] = true;
            }
        }
    }
    for(int i = 0; i < pairs.length-1; i++){
        if(!pairs[i]) return false;
    }
    return true;
}