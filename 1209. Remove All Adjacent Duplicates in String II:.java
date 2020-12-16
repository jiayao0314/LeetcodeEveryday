// 1209. Remove All Adjacent Duplicates in String II/m

// use two pointers to keep the old stack and the new stack at the same time,
// i is faster, and will move back, j is slower
public String removeDuplicates(String s, int k) {
    int i = 0, n = s.length(), count[] = new int[n];
    char[] stack = s.toCharArray();
    for (int j = 0; j < n; ++j, ++i) {
        stack[i] = stack[j];
        count[i] = i > 0 && stack[i - 1] == stack[j] ? count[i - 1] + 1 : 1;
        if (count[i] == k) i -= k;
    }
    return new String(stack, 0, i);
}

// same thinking use StringBuilder
public String removeDuplicates(String s, int k) {
    final StringBuilder sb = new StringBuilder(s);
    final int count[] = new int[sb.length()];
    for (int i = 0; i < sb.length(); ++i) {
        if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
            count[i] = 1;
        } else {
            count[i] = count[i - 1] + 1;
            if (count[i] == k) {
                sb.delete(i - k + 1, i + 1);
                i = i - k;
            }
        }
    }
    return sb.toString();
}

// recursion
public String removeDuplicates(String s, int k) {
    if(s.length() < k) return s;
    
    int count = 1;
    
    for(int i = 0; i< s.length() -1; i++){
        if(s.charAt(i) == s.charAt(i+1)) count++;
        else count = 1;
        // concatenate the left and the new right
        if(count == k) s = removeDuplicates(s.substring(0,i+1-k+1) + s.substring(i+2), k);
        
    }
    
    return s;
}