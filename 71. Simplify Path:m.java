// 71. Simplify Path/m

// 12/15/20, 结果想要从栈底往上输出，就用 Deque - LinkedList 实现 stack。
// 或者直接 for 遍历 Stack<>, 能从栈底往上输出【找时间看源码】

class Solution {
    public String simplifyPath(String path) {
        StringBuilder sb = new StringBuilder();
        Deque<String> stack = new LinkedList<>();
        for(String s: path.split("/")) {
            if(!stack.isEmpty() && s.equals("..")) {
                stack.pop();
            } else if(!s.equals(".") && !s.equals("") && !s.equals("..")) {
                stack.push(s);
            }
        }
        if(stack.isEmpty()) return "/";
        while(!stack.isEmpty()) {
            sb.append('/' + stack.pollLast());
        }
        return sb.toString();
    }

    public String simplifyPath(String path) {
       
        String[] dirs = path.split("/");
        Stack<String> stack = new Stack<>();
        for(String dir:dirs)
        {
            if(dir.isEmpty() || dir.equals("."))
                continue;
            if(dir.equals(".."))
            {
                if(stack.isEmpty())
                   continue;
                stack.pop();
                continue;
            }
            stack.push(dir);
        }
        
        StringBuilder sb = new StringBuilder();
        // 直接 for 遍历，从栈底往上输出
        for(String dir : stack)
        {
            sb.append("/");
            sb.append(dir);
            
        }
        return sb.length() > 0 ? sb.toString() : "/" ;
    }
}