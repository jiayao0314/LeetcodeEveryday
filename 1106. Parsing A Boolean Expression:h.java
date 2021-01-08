// 1106. Parsing A Boolean Expression/h
public boolean parseBoolExpr(String expression) {
    Stack<Character> stack = new Stack<>();
    for(int i = 0; i < expression.length(); i++) {
        char c = expression.charAt(i);
        if(c == ')') {
            Set<Character> shown = new HashSet<>();
            while(stack.peek() != '(') {
                shown.add(stack.pop());
            }
            stack.pop();
            char operator = stack.pop();
            if(operator == '&') {
                stack.push(shown.contains('f') ? 'f' : 't');
            } else if(operator == '|') {
                stack.push(shown.contains('t') ? 't' : 'f');
            } else if(operator == '!') {
                stack.push(shown.contains('t') ? 'f' : 't');
            }
        } else if(c != ',') {
            stack.push(c);
        }
    }
    return stack.pop() == 't';
}