// 464. Can I Win/m

// Input: maxChoosableInteger = 10, desiredTotal = 11
// Output: false
// Explanation:
// No matter which integer the first player choose, the first player will lose.


class Solution {
    // public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    
    /*
    两人选数字后，递减 target，看最终谁选数字的时候正好能选一个大于等于 target 的数字
    一组 boolean 维护选过的数字，map 记录当前选法的结果，key-string-是 boolean arr 变为 str
    */
    
    Map<String, Boolean> map;
    
    public boolean canIWin(int max, int target) {
        if(target <= max) return true;
        if(((1 + max) / 2 * max) < target) return false; // 全部选完都不够
        map = new HashMap<>();
        boolean[] used = new boolean[max + 1];
        return canwin(max, target, used);
    }
    
    public boolean canwin(int max, int target, boolean[] used) {
        if(target <= 0) return false;
        String str = Arrays.toString(used);
        if(map.containsKey(str)) {
            return map.get(str);
        }
        for(int i = 1; i <= max; i++) {
            if(used[i]) continue;
            used[i] = true;
            if(!canwin(max, target - i, used)) {
                map.put(str, true);
                used[i] = false;
                return true;
            }
            used[i] = false;
        }
        map.put(str, false);
        return false;
    }
    
    
    //  直接用 boolean + bit 实现 used 和 map 的功能，快，但是没上面的好理解
    int max;
    Boolean[] cache;
    public boolean canIWin(int max, int target) {

        if(target == 0 || max > target) return true;
        int sum = (1 + max) * max / 2;
        if(sum < target) return false;

        this.max = max;
        cache = new Boolean[1 << max];
        return dfs(target, 0);
    }

    boolean dfs(int total, int state){
        if(cache[state] != null) return cache[state];
        for(int i = 0; i < max; i++){
            if((state & (1 << i)) != 0) continue;
            if(total <= i + 1 || !dfs(total - i - 1, state | (1 << i))){
                cache[state] = true;
                return true;
            }
            cache[state] = false; 
        }
        cache[state] = false;
        return false;
    } 
}