// 992. Subarrays with K Different Integers/h
public int subarraysWithKDistinct(int[] A, int K) {
    return atMost(A, K) - atMost(A, K - 1);
}

private int atMost(int[] A, int K) {
    HashMap<Integer, Integer> map = new HashMap<>();
    int res = 0, j = 0;
    for(int i = 0; i < A.length; i++) {
        if(map.getOrDefault(A[i], 0) == 0) K--;
        map.put(A[i], map.getOrDefault(A[i], 0) + 1);
        while(K < 0) {
            map.put(A[j], map.get(A[j]) - 1);
            if(map.get(A[j]) == 0) K++;
            j++;
        }
        res += i - j + 1;
    }
    return res;
}