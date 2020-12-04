// 636. Exclusive Time of Functions/m

class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Integer> stk = new Stack<>();
        int prev = 0;
        for(String log: logs) {
            String[] parts = log.split(":");
            if(!stk.isEmpty()) res[stk.peek()] += Integer.parseInt(parts[2]) - prev;
            prev = Integer.parseInt(parts[2]);
            int curID = Integer.parseInt(parts[0]);
            if(parts[1].equals("start"))
                stk.push(curID);
            else {
                res[curID]++;
                stk.pop();
                // res[stk.pop()]++;
                prev++;
            }
        }
        return res;
    }
}