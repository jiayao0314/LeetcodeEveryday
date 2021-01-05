// 159. Longest Substring with At Most Two Distinct Characters/m


// shoud use if(map.get(cur) == 1) count++;
public int lengthOfLongestSubstringTwoDistinct(String s) {
    if(s == null) return 0;
    if(s.length() <= 2) return s.length();
    int ans = 0, count = 0, j = 0;
    Map<Character, Integer> map = new HashMap<>();
    for(int i = 0; i < s.length(); i++) {
        char cur = s.charAt(i);
        map.put(cur, map.getOrDefault(cur, 0) + 1);
        if(map.get(cur) == 1) count++;
        while(count > 2 && j < i) {
            char left = s.charAt(j);
            map.put(left, map.get(left) - 1);
            if(map.get(left) == 0) count--;
            j++;
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}