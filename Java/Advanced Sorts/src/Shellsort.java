import java.util.Random;

public class Shellsort {
    private static int comparison = 0;
    private static int copies = 0;
    private static int swaps = 0;
    //Define a gap between elements to be compared and swapped
    public static void sort(int[] array) {
        int h = 1;
        int temp, i, j;
        int n = array.length;
        while(h <= n / 3) {
            h = h * 3 + 1; //Using Knuth's sequence to determine initial gap
        }
        while (h > 0) {
            for(i = h; i < n; i++) {
                temp = array[i];
                copies++;
                j = i;
                while (j >= h && array[j - h] > temp) {
                    array[j] = array[j - h];
                    j -= h;
                    copies++;
                    comparison++;
                }
                array[j] = temp;
                copies++;
                if (j != i) {
                    swaps++;
                }
            }
            h = (h - 1) / 3; //Reduce the gap for the next pass        }            
        }
    }    
    public static void main(String[] args) {
        int[] array = new int[20000];
        Random  rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(50000); // Fill array with random integers
        }
        sort(array);
        System.out.println("Number of operations: " + copies + "/" + comparison + "/" + swaps);
    }

    
}
