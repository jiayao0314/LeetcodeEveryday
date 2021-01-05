// 3. Longest Substring Without Repeating Characters/m

public int lengthOfLongestSubstring(String s) {
    if(s == null || s.length() == 0) return 0;
    Map<Character, Integer> map = new HashMap<>();
    int ans = 0, j = 0;
    for(int i = 0; i < s.length(); i++) {
        if(map.containsKey(s.charAt(i))) {
            j = Math.max(j, map.get(s.charAt(i)) + 1);
        }
        ans = Math.max(ans, i - j + 1);
        map.put(s.charAt(i), i);
    }
    return ans;
}