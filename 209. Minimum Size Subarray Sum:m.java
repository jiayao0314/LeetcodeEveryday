// 209. Minimum Size Subarray Sum/m


class Solution {
    public int minSubArrayLen(int s, int[] nums) {
//         if(nums == null || nums.length == 0) return 0;
//         int cnt = Integer.MAX_VALUE;
        
//         int i = 0, j = 0, sum = 0;
//         while(i < nums.length) {
//             sum += nums[i];
//             while(sum >= s) {
//                 cnt = Math.min(cnt, i-j+1);
//                 sum -= nums[j++];
//             }
//             i++;
//         }
//         return cnt == Integer.MAX_VALUE? 0 : cnt;
        
        if(nums == null || nums.length == 0) return 0;
        int sum = 0, ans = Integer.MAX_VALUE;
        int j = 0;
        for(int i = 0; i< nums.length; i++) {
            sum += nums[i];
            while(sum >= s) {
                ans = Math.min(ans, i - j + 1);
                sum -= nums[j];
                j++;
            }
        }
        if(ans == Integer.MAX_VALUE) return 0;
        return ans;
    }
}