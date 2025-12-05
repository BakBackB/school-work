import java.util.InputMismatchException;
import java.util.Scanner;

public class CircleLinkList<E> {
    private Link<E> first;

    public CircleLinkList() {
        first = null;
    }

    public void insert(E dd) {
        Link<E> newLink = new Link<>(dd);
        if (isEmpty()) {
            first = newLink;
            newLink.next = first;
        } else {
            Link<E> current = first;
            while (current.next != first) {
                current = current.next;
            }
            current.next = newLink;
            newLink.next = first;
        }   
    }
    public void removeByCounter(int counterOff, int start) {
        int count = 1;
        Link<E> current = first;
        Link<E> previous = current;
        System.out.println("Elimination order:");
        for(int i = 1; i < start; i++) {
            previous = current;
            current = current.next;
        }
        if(current.next == current) {
            System.out.print(current.dData + " ");
            first = null;
        } else {
            while (current.next != current) {
                if (count % counterOff == 0) {
                    System.out.print(current.dData + " ");
                    if (current == first) {
                        first = first.next;
                    }
                    previous.next = current.next;
                }
                previous = current;
                current = current.next;
                count++;
            }
        }
    }
    public boolean isEmpty() {
        return first == null;
    }

    public void display() {
        if(isEmpty()){
            System.out.println("There is no one at all");
        } else {
            Link<E> current = first;
            do {
                current.displayLink();
                current = current.next;
            } while (current != first);
        }
    }
}

class CircleLinkListApp {
    public static void main(String[] args) {
        CircleLinkList<Integer> circleLinkList = new CircleLinkList<>();
        boolean valid = false;
        Scanner scanner = new Scanner(System.in);
        int countOff = 0, start = 0, maxSolider = 0;

        while (!valid) {
            try {
                System.out.print("Enter the number of people in the circle: ");
                maxSolider = scanner.nextInt();

                System.out.print("Enter the number used for counting off: ");
                countOff = scanner.nextInt();

                System.out.print("Enter the number of person where counting starts: ");
                start = scanner.nextInt();

                if (maxSolider >= countOff && countOff > 0 && start > 0) {
                    valid = true;
                } else {
                    System.out.println("Invalid input! Please ensure that:");
                    System.out.println("maxSolider >= countOff >= start > 0");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter valid integers!");
                scanner.nextLine();
            }
        }
        for(int i = 1; i <= maxSolider; i++) {
            circleLinkList.insert(i);
        }
        circleLinkList.removeByCounter(countOff, start);
        System.out.println();
        System.out.println("Last person standing: ");
        circleLinkList.display();

    }
}