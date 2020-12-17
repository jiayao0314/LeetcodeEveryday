// 227. Basic Calculator II

// calculate until next number finished, meeting next operator, 
// so need to keep the most recent operator and keep replacing
class Solution {
    public int calculate(String s) {
        if(s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        char op = '+';
        s += "+";
        int num = 0;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
                continue;
            } else if(ch == ' ') continue;
            else if(op == '+') stack.push(num);
            else if(op == '-') stack.push(-num);
            else if(op == '*') stack.push(stack.pop() * num);
            else if(op == '/') stack.push(stack.pop() / num);
            op = ch;
            num = 0;
        }
        int res = 0;
        while(!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}