// 224. Basic Calculator/h

// calculate after number, use the stack to store sign and tmp sum
public static int calculate(String s) {
	int len = s.length(), sign = 1, result = 0;
	Stack<Integer> stack = new Stack<Integer>();
	for (int i = 0; i < len; i++) {
		if (Character.isDigit(s.charAt(i))) {
			int sum = s.charAt(i) - '0';
			while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
				sum = sum * 10 + s.charAt(i + 1) - '0';
				i++;
			}
			result += sum * sign;
		} else if (s.charAt(i) == '+')
			sign = 1;
		else if (s.charAt(i) == '-')
			sign = -1;
		else if (s.charAt(i) == '(') {
			stack.push(result);
			stack.push(sign);
			result = 0;
			sign = 1;
		} else if (s.charAt(i) == ')') {
			result = result * stack.pop() + stack.pop();
		}

	}
	return result;
}

// calculate after +/-, use the stack to store sign
public int calculate(String s) {
    if(s == null) return 0;
        
    int result = 0;
    int sign = 1;
    int num = 0;
            
    Stack<Integer> stack = new Stack<Integer>();
    stack.push(sign);
            
    for(int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
                
        if(c >= '0' && c <= '9') {
            num = num * 10 + (c - '0');
                    
        } else if(c == '+' || c == '-') {
            result += sign * num;
            sign = stack.peek() * (c == '+' ? 1: -1); 
            num = 0;
                    
        } else if(c == '(') {
            stack.push(sign);
                    
        } else if(c == ')') {
            stack.pop();
        }
    }
            
    result += sign * num;
    return result;
}