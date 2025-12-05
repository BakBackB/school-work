import java.util.Stack;

public class InfixToPostfix {
    public int checkOperatorPriority(char c) {
        if(c == '+' || c == '-') return 1;
        if(c == '*' || c == '/') return 2;
        return -1;
    }

    public boolean isLetterOrDigit(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9';
    }
    
    public String infixToPostfix(String s) {
        s = s.replaceAll("\\s", "");
        Stack<Character> stack = new Stack<>();
        StringBuilder str = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(isLetterOrDigit(c)) {
                str.append(c);
            } else if(c == '(') {
                stack.push(c);
            } else if(c == ')') {
                while(!stack.isEmpty() && stack.peek() != '(') {
                    str.append(' ');
                    str.append(stack.pop());
                }
                stack.pop();
            } else {
                while(!stack.isEmpty() && checkOperatorPriority(c) <= checkOperatorPriority(stack.peek())) {
                    str.append(' ');
                    str.append(stack.pop());
                }
                stack.push(c);
                str.append(' ');
            }
        }
        while(!stack.isEmpty()) {
            str.append(' ');
            str.append(stack.pop());
        }

        return str.toString();
    }

    public static void main(String[] args) {
        InfixToPostfix converter = new InfixToPostfix();
        String infix = "100 + 6*20/3+(1-8)";

        System.out.println("Infix expression: " + infix);
        String postfix = converter.infixToPostfix(infix);
        System.out.println("Postfix expression: " + postfix);
    }
}
