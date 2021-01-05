// 1658. Minimum Operations to Reduce X to Zero/m

public int minOperations(int[] nums, int x) {
    int sum = 0;
    for(int n: nums) sum += n;
    int target = sum - x;
    int tmp = 0, i = 0;
    int ans = -1;
    for(int j = 0; j < nums.length; j++) {
        tmp += nums[j];
        while(tmp > target && i <= j) {
            tmp -= nums[i];
            i++;
        } 
        if(tmp == target) ans = Math.max(ans, j - i + 1);
    }
    if(ans == -1) return -1;
    return nums.length - ans;
}