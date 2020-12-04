// 402. Remove K Digits/m

class Solution {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        // 递增单调栈，尽量让栈底（字符串开头）的数字最小
        for(Character c: num.toCharArray()) {
            while(!stack.isEmpty() && k > 0 && stack.peek() > c) {
                stack.pop();
                k--;
            }
            stack.push(c);
        }
        // k 剩余，那就从栈顶（最大）的数字开始 pop
        while(k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        if(stack.isEmpty()) return "0";
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        int nonZero = -1;
        sb = sb.reverse();
        for(int i = 0; i < sb.length(); i++) {
            if(sb.charAt(i) == '0' && nonZero < i) {
                nonZero++;
            } else if(sb.charAt(i) != 0) {
                break;
            }
        }
        // 剩余的全是 0 
        if(nonZero + 1 == sb.length()) return "0";
        return sb.toString().substring(nonZero + 1, sb.length());
    }
}