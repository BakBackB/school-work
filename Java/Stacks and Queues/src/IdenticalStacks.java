import java.util.Stack;
public class IdenticalStacks {
    public static <T> boolean areStacksIdentical(Stack<T> s1, Stack<T> s2) {
        if (s1 == null || s2 == null) {
            return s1 == s2; // Both null or one null and one not
        }
        if (s1.size() != s2.size()) {
            return false; // Different sizes, cannot be identical
        }

        Stack<T> tempStack1 = new Stack<>();
        Stack<T> tempStack2 = new Stack<>();
        boolean identical = true;

        while (!s1.isEmpty()) {
            T element1 = s1.pop();
            T element2 = s2.pop();

            if (!element1.equals(element2)) {
                identical = false;
            }

            tempStack1.push(element1);
            tempStack2.push(element2);
        }

        // Restore the original stacks
        while (!tempStack1.isEmpty()) {
            s1.push(tempStack1.pop());
        }
        while (!tempStack2.isEmpty()) {
            s2.push(tempStack2.pop());
        }

        return identical;
    }
}