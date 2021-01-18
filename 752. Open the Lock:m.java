// 752. Open the Lock/m

public int openLock(String[] deadends, String target) {
    Set<String> seen = new HashSet<>(Arrays.asList(deadends));
    Queue<String> q = new ArrayDeque<>();
    q.offer("0000");
    int turn = 0;
    while(!q.isEmpty()) {
        int size = q.size();
        for(int i = 0; i < size; i++) {
            String cur = q.poll();
            if(seen.contains(cur)) continue;
            seen.add(cur);
            if(cur.equals(target)) return turn;
            for(int j = 0; j < cur.length(); j++) {
                String up = turnUp(cur, j);
                String down = turnDown(cur, j);
                if(!seen.contains(up)) {
                    q.offer(up);
                }
                if(!seen.contains(down)) {
                    q.offer(down);
                }
            }
        }
        turn++;
    }
    return -1;
}

private String turnUp(String s, int i) {
    char[] chs = s.toCharArray();
    if(chs[i] == '9') chs[i] = '0';
    else chs[i] += 1;
    return String.valueOf(chs);
}

private String turnDown(String s, int i) {
    char[] chs = s.toCharArray();
    if(chs[i] == '0') chs[i] = '9';
    else chs[i] -= 1;
    return String.valueOf(chs);
}