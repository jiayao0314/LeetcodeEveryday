// 127. Word Ladder/h

// return transformation sequence, so transfer = 1 + step

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if(beginWord.equals(endWord)) return 0;
        Set<String> wordSet = new HashSet<>(wordList);
        wordSet.remove(beginWord);
        if(endWord.length() != beginWord.length() || !wordSet.contains(endWord)) return 0;
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int transfer = 1;
        while(!q.isEmpty()) {
            for(int size = q.size(); size > 0; size--) {
                String cur = q.poll();
                if(cur.equals(endWord)) return transfer;
                char[] curArr = cur.toCharArray();
                for(int i = 0; i < curArr.length; i++) {
                    char old = curArr[i];
                    for(char ch = 'a'; ch <= 'z'; ch++) {
                        if(ch != old) {
                            curArr[i] = ch;
                            // curArr.toString() 不行？
                            String changed = String.valueOf(curArr);
                            if(wordSet.contains(changed) && !visited.contains(changed)) {
                                q.offer(changed);
                                visited.add(changed);
                            }
                        }
                    }
                    curArr[i] = old;
                }
            }
            transfer++;
        }
        return 0;
    }
    
    // bi-bfs
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
      Set<String> dict = new HashSet<>(wordList), q1 = new HashSet<>(), q2 = new HashSet<>(), vis = new HashSet<>();
      q1.add(beginWord);
      if (dict.contains(endWord)) q2.add(endWord); // all transformed words must be in dict (including endWord)
      for (int len = 2; !q1.isEmpty(); len++) {
        Set<String> q = new HashSet<>();
        for (String w : q1) {
          for (int j = 0; j < w.length(); j++) {
            char[] ch = w.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
              if (c == w.charAt(j)) continue; // beginWord and endWord not the same, so bypass itself
              ch[j] = c;
              String nb = String.valueOf(ch);
              if (q2.contains(nb)) return len; // meet from two ends
              if (dict.contains(nb) && vis.add(nb)) q.add(nb); // not meet yet, vis is safe to use
            }
          }
        }
        q1 = (q.size() < q2.size()) ? q : q2; // switch to small one to traverse from other end
        q2 = (q1 == q) ? q2 : q;
      }
      return 0;
    }
}