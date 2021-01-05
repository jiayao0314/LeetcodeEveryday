// 1562. Find Latest Group of Size M/m

public int findLatestStep(int[] arr, int m) {
    int res = -1;
    int n = arr.length;
    int[] len = new int[n + 2], count = new int[n + 1];
    for(int i = 0; i < n; i++) {
        int cur = arr[i], left = len[cur - 1], right = len[cur + 1];
        len[cur] = len[cur - left] = len[cur + right] = left + right + 1;
        count[left]--;
        count[right]--;
        count[len[cur]]++;
        if(count[m] > 0)
            res = i + 1;
    }
    return res;
}