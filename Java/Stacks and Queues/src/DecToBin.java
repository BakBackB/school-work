import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class DecToBin {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        int num = 0, quotient;
        Stack<Integer> stack = new Stack<>();

        while(!valid) {
            System.out.print("Please enter a decimal number: ");

            try {
                num = scanner.nextInt();
                valid = true;
            } catch(InputMismatchException e) {
                System.out.println("Please enter a valid number");
                scanner.nextLine();
            }
        }
        scanner.close();
        if(num == 0) {
            System.out.print("The given decimal number is converted to binary number as: " + num);
        } else {
            while(num != 0) {
                stack.push(num%2);
                num /= 2;
            }
            System.out.print("The given decimal number is converted to binary number as: ");
            while(!stack.isEmpty()) {
                System.out.print(stack.pop());
            }
        }
    }
}