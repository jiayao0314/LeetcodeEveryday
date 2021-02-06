// 847. Shortest Path Visiting All Nodes/h

// bfs, dp - faster, only add the Node with shorter path to the queue

class Solution {
    class Node {
        int id; int mask;
        
        public Node(int i, int m) {
            id = i;
            mask = m;
        }
        
        public String toString() {
            return id + " " + mask;
        }
    }
    
    public int shortestPathLength(int[][] graph) {
        Queue<Node> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int target = (1 << graph.length) - 1;
        for(int i = 0; i < graph.length; i++) {
            Node node = new Node(i, 1 << i);
            visited.add(node.toString());
            q.offer(node);
        }
        int step = 0;
        while(!q.isEmpty()) {
            for(int size = q.size(); size > 0; size--) {
                Node cur = q.poll();
                if(cur.mask == target) return step;
                for(int nei: graph[cur.id]) {
                    Node next = new Node(nei, cur.mask | (1 << nei));
                    if(visited.contains(next.toString())) continue;
                    q.offer(next);
                    visited.add(next.toString());
                }
            }
            step++;
        }
        return step;
    }
    
    
    // dp
    public int shortestPathLength(int[][] graph) {
        final int N = (graph == null) ? 0 : graph.length;
        if (N <= 1) {
            return 0;
        }

        Queue<State> queue = new LinkedList<>();
        int[][] dp = new int[N][1 << N];

        for (int i = 0; i < N; ++i) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
            queue.offer(new State(i, 1 << i));
            dp[i][1 << i] = 0;
        }

        final int fullMask = (1 << N) - 1;
        while (!queue.isEmpty()) {
            State curr = queue.poll();

            if (curr.mask == fullMask) {
                return dp[curr.node][curr.mask];
            }

            for (int next : graph[curr.node]) {
                int mask = curr.mask | (1 << next);
                if (dp[next][mask] > dp[curr.node][curr.mask] + 1) {
                    dp[next][mask] = dp[curr.node][curr.mask] + 1;
                    queue.offer(new State(next, mask));
                }
            }
        }

        return -1;
    }

    static class State {
        final int node;
        final int mask;

        State(int node, int mask) {
            this.node = node;
            this.mask = mask;
        }
    }
}