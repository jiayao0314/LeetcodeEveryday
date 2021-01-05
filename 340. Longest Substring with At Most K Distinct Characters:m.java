// 340. Longest Substring with At Most K Distinct Characters/m

public int lengthOfLongestSubstringKDistinct(String s, int k) {
    if(s.length() <= k) return s.length();
    Map<Character, Integer> map = new HashMap<>();
    int ans = 0, count = 0, j = 0;
    for(int i = 0; i < s.length(); i++) {
        char cur = s.charAt(i);
        map.put(cur, map.getOrDefault(cur, 0) + 1);
        if(map.get(cur) == 1) count++;
        while(count > k) {
            char left = s.charAt(j);
            map.put(left, map.get(left) - 1);
            if(map.get(left) == 0) count--;
            j++;
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}