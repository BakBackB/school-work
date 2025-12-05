public class SierpinskiRecursive {
    public void printSierpinski(int n, int y) {
        if (y < 0) {
            return;
        }
        printSpaces(y);
        printLine(0, n, y);  
        System.out.println();
        printSierpinski(n, y - 1);
    }

    public void printSpaces(int count) {
        if (count == 0) {
            return;
        }
        System.out.print(" ");
        printSpaces(count - 1);
    }

    public void printLine(int x, int numberOfRows, int y) {
        if (x + y >= numberOfRows) {
            return;
        }
        if ((x & y) != 0) {
            System.out.print("  ");
        } else {
            System.out.print("* ");
        }
        printLine(x + 1, numberOfRows, y);
    }
}