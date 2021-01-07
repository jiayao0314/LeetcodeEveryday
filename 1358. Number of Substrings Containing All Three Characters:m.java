// 1358. Number of Substrings Containing All Three Characters/m

public int numberOfSubstrings(String s) {
    // Map<Character, Integer> map = new HashMap<>();
    int[] count = new int[3];
    int l = -1, r = 0;
    int ans = 0;
    for(; r < s.length(); r++) {
        count[s.charAt(r) - 'a']++;
        while(count[0] > 0 && count[1] > 0 && count[2] > 0) {
            ans += s.length() - r;
            l++;
            count[s.charAt(l) - 'a']--;
        }
    }
    return ans;
}