// 395. Longest Substring with At Least K Repeating Characters/m

public int longestSubstring(String s, int k) {
    int[] chs = new int[26];
    for(char ch: s.toCharArray()) chs[ch - 'a']++;
    boolean flag = true;
    for(int i = 0; i < 26; i++) {
        if(chs[i] != 0 && chs[i] < k) flag = false;
    }
    if(flag) return s.length();
    int start = 0, cur = 0;
    int res = 0;
    while(cur < s.length()) {
        if(chs[s.charAt(cur) - 'a'] < k) {
            res = Math.max(res, longestSubstring(s.substring(start, cur), k));
            start = cur + 1;
        }
        cur++;
    }
    res = Math.max(res, longestSubstring(s.substring(start), k));
    return res;
}

// i, j move in different conditions
public int longestSubstring(String s, int k) {
    char[] chs = s.toCharArray();
    int[] count = new int[26];
    int h, i, j, unique, noLessK, len = 0;
    for(h = 1; h <= 26; h++) {
        Arrays.fill(count, 0);
        i = 0;
        j = 0;
        unique = 0;
        noLessK = 0;
        while(j < s.length()) {
            if(unique <= h) {
                char cur = chs[j];
                count[cur - 'a']++;
                if(count[cur - 'a'] == 1) unique++;
                if(count[cur - 'a'] == k) noLessK++;
                j++;
            } else {
                char left = chs[i];
                count[left - 'a']--;
                if(count[left - 'a'] == 0) unique--;
                if(count[left - 'a'] == k - 1) noLessK--;
                i++;
            }
            if(unique == h && unique == noLessK) {
                len = Math.max(len, j - i);
            }
        }
    }
    return len;
}