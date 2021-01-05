/*
1371. Find the Longest Substring Containing Vowels in Even Counts/m
sliding window: 
the window start does not just advance, but depends on the running state
560. Subarray Sum Equals K (see approach 4 in the solution)
use a hash map to track when the running state repeats, use a bit mask as a state, 
and store the minimum index - because we are trying to maximize the window size.
*/

public int findTheLongestSubstring(String s) {
    Map<Character, Integer> map = new HashMap<>();
    map.put('a', 1);
    map.put('e', 2);
    map.put('i', 4);
    map.put('o', 8);
    map.put('u', 16);
    Map<Integer, Integer> stateMap = new HashMap<>();
    stateMap.put(0, -1);
    int state = 0, ans = Integer.MIN_VALUE;
    for(int i = 0; i < s.length(); i++) {
        char cur = s.charAt(i);
        if(map.containsKey(cur)) {
            state ^= map.get(cur);
            
        }
        stateMap.putIfAbsent(state, i);
        ans = Math.max(ans, i - stateMap.get(state));
    }
    return ans;
}