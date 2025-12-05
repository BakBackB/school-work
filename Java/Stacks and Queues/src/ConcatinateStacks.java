import java.util.Stack;

public class ConcatinateStacks {

    public static Stack<Integer> concatenateStacks(Stack<Integer> stack1, Stack<Integer> stack2) {
        Stack<Integer> mergedStack = new Stack<>();
        Stack<Integer> tempStack1 = new Stack<>();
        Stack<Integer> tempStack2 = new Stack<>();

        // Transfer elements from stack1 to tempStack1 (reversing order)
        while (!stack1.isEmpty()) {
            tempStack1.push(stack1.pop());
        }

        // Transfer elements from tempStack1 to mergedStack (restoring order)
        while (!tempStack1.isEmpty()) {
            mergedStack.push(tempStack1.pop());
        }

        // Transfer elements from stack2 to tempStack2 (reversing order)
        while (!stack2.isEmpty()) {
            tempStack2.push(stack2.pop());
        }

        // Transfer elements from tempStack2 to mergedStack (restoring order)
        while (!tempStack2.isEmpty()) {
            mergedStack.push(tempStack2.pop());
        }

        return mergedStack;
    }
}

class ConcatinateMain {
    public static void main(String[] arg) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        for(int i = 0; i < 6; i++) {
            stack1.push(i*2);
            stack2.push(i*3);
        }
        System.out.println(ConcatinateStacks.concatenateStacks(stack1,stack2));
    }
}